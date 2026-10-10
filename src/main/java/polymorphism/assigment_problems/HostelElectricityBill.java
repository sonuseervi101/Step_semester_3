package polymorphism.assigment_problems;

import java.util.Scanner;

public class HostelElectricityBill {

    // Base class: every room has units used and calculates its own bill
    static abstract class Room {
        protected final int units;

        Room(int units) {
            this.units = units;
        }

        abstract String getType();

        abstract double calculateBill();
    }

    static class SingleRoom extends Room {
        SingleRoom(int units) { super(units); }

        @Override
        String getType() { return "SINGLE"; }

        @Override
        double calculateBill() { return 8.0 * units; }
    }

    static class SharedRoom extends Room {
        private final int occupants;   // extra value stored only where it's needed

        SharedRoom(int units, int occupants) {
            super(units);
            this.occupants = occupants;
        }

        @Override
        String getType() { return "SHARED"; }

        @Override
        double calculateBill() { return (6.0 * units) / occupants; }   // each occupant's share
    }

    static class AcRoom extends Room {
        AcRoom(int units) { super(units); }

        @Override
        String getType() { return "AC"; }

        @Override
        double calculateBill() { return 10.0 * units + 200; }   // plus fixed Rs 200
    }

    // Factory: reads the right number of values for each type
    static Room createRoom(String type, Scanner sc) {
        int units = sc.nextInt();
        return switch (type) {
            case "SINGLE" -> new SingleRoom(units);
            case "SHARED" -> new SharedRoom(units, sc.nextInt());
            case "AC" -> new AcRoom(units);
            default -> throw new IllegalArgumentException("Unknown room type: " + type);
        };
    }

    public static void main(String[] args) {
        String input = """
                3
                SINGLE 120
                SHARED 150 3
                AC 100
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        Room[] rooms = new Room[n];
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            rooms[i] = createRoom(type, sc);
        }

        // Uniform processing: no type checks
        double total = 0;
        for (Room r : rooms) {
            double bill = r.calculateBill();
            System.out.printf("%s: %.2f%n", r.getType(), bill);
            total = total + bill;
        }
        System.out.printf("Total: %.2f%n", total);
    }
}