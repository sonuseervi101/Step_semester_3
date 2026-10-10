package polymorphism.class_problems;

import java.util.Scanner;

public class DeliveryFeeCalculator {

    // Base class: every delivery has weight and distance, and calculates its own fee
    static abstract class Delivery {
        protected final double weight;
        protected final double distance;

        Delivery(double weight, double distance) {
            this.weight = weight;
            this.distance = distance;
        }

        abstract String getType();

        abstract double calculateFee();
    }

    static class StandardDelivery extends Delivery {
        StandardDelivery(double weight, double distance) { super(weight, distance); }

        @Override
        String getType() { return "STANDARD"; }

        @Override
        double calculateFee() { return 5 + 0.50 * weight + 0.10 * distance; }
    }

    static class ExpressDelivery extends Delivery {
        ExpressDelivery(double weight, double distance) { super(weight, distance); }

        @Override
        String getType() { return "EXPRESS"; }

        @Override
        double calculateFee() { return 15 + 1.00 * weight + 0.20 * distance; }
    }

    static class InternationalDelivery extends Delivery {
        private final double customsFee;   // extra value only this type needs

        InternationalDelivery(double weight, double distance, double customsFee) {
            super(weight, distance);
            this.customsFee = customsFee;
        }

        @Override
        String getType() { return "INTERNATIONAL"; }

        @Override
        double calculateFee() { return 25 + 2.00 * weight + 0.50 * distance + customsFee; }
    }

    // Factory: reads the right number of values for each type and builds the object
    static Delivery createDelivery(String type, Scanner sc) {
        double weight = sc.nextDouble();
        double distance = sc.nextDouble();
        return switch (type) {
            case "STANDARD" -> new StandardDelivery(weight, distance);
            case "EXPRESS" -> new ExpressDelivery(weight, distance);
            case "INTERNATIONAL" -> new InternationalDelivery(weight, distance, sc.nextDouble());
            default -> throw new IllegalArgumentException("Unknown delivery type: " + type);
        };
    }

    public static void main(String[] args) {
        String input = """
                3
                STANDARD 10 50
                EXPRESS 5 20
                INTERNATIONAL 20 100 30
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        Delivery[] deliveries = new Delivery[n];
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            deliveries[i] = createDelivery(type, sc);
        }

        // Uniform processing: no type checks
        double total = 0;
        for (Delivery d : deliveries) {
            double fee = d.calculateFee();
            System.out.printf("%s: %.2f%n", d.getType(), fee);
            total = total + fee;
        }
        System.out.printf("Total: %.2f%n", total);
    }
}