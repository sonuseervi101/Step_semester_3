package polymorphism.assigment_problems;

import java.util.Scanner;

public class ParkingChargeCalculator {

    // Base class: every vehicle has parked hours and calculates its own charge
    static abstract class Vehicle {
        protected final int hours;

        Vehicle(int hours) {
            this.hours = hours;
        }

        abstract String getType();

        abstract double calculateCharge();
    }

    static class Bike extends Vehicle {
        Bike(int hours) { super(hours); }

        @Override
        String getType() { return "BIKE"; }

        @Override
        double calculateCharge() { return 10.0 * hours; }
    }

    static class Car extends Vehicle {
        Car(int hours) { super(hours); }

        @Override
        String getType() { return "CAR"; }

        @Override
        double calculateCharge() { return 30 + 20.0 * (hours - 1); }   // first hour 30, then 20 each
    }

    static class Truck extends Vehicle {
        Truck(int hours) { super(hours); }

        @Override
        String getType() { return "TRUCK"; }

        @Override
        double calculateCharge() { return Math.max(50.0 * hours, 100); }   // minimum Rs 100
    }

    // Factory: the only place that looks at the type text
    static Vehicle createVehicle(String type, int hours) {
        return switch (type) {
            case "BIKE" -> new Bike(hours);
            case "CAR" -> new Car(hours);
            case "TRUCK" -> new Truck(hours);
            default -> throw new IllegalArgumentException("Unknown vehicle type: " + type);
        };
    }

    public static void main(String[] args) {
        String input = """
                4
                BIKE 3
                CAR 4
                TRUCK 1
                CAR 1
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        Vehicle[] vehicles = new Vehicle[n];
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int hours = sc.nextInt();
            vehicles[i] = createVehicle(type, hours);
        }

        // Uniform processing: no type checks
        double total = 0;
        for (Vehicle v : vehicles) {
            double charge = v.calculateCharge();
            System.out.printf("%s: %.2f%n", v.getType(), charge);
            total = total + charge;
        }
        System.out.printf("Total: %.2f%n", total);
    }
}