package polymorphism.class_problems;

import java.util.Scanner;

public class TransportFareCalculator {

    // Base class: every journey has a distance and calculates its own fare
    static abstract class Transport {
        protected final double distance;

        Transport(double distance) {
            this.distance = distance;
        }

        abstract String getType();

        abstract double calculateFare();
    }

    static class Bus extends Transport {
        Bus(double distance) { super(distance); }

        @Override
        String getType() { return "BUS"; }

        @Override
        double calculateFare() {
            double fare = 2 + 0.10 * distance;
            return Math.min(fare, 10);   // fare is capped at $10
        }
    }

    static class Train extends Transport {
        Train(double distance) { super(distance); }

        @Override
        String getType() { return "TRAIN"; }

        @Override
        double calculateFare() { return 3 + 0.15 * distance; }
    }

    static class Metro extends Transport {
        private final double peakHourFactor;   // extra value only Metro needs

        Metro(double distance, double peakHourFactor) {
            super(distance);
            this.peakHourFactor = peakHourFactor;
        }

        @Override
        String getType() { return "METRO"; }

        @Override
        double calculateFare() { return (1.50 + 0.20 * distance) * peakHourFactor; }
    }

    // Factory: reads the right number of values for each type
    static Transport createJourney(String type, Scanner sc) {
        double distance = sc.nextDouble();
        return switch (type) {
            case "BUS" -> new Bus(distance);
            case "TRAIN" -> new Train(distance);
            case "METRO" -> new Metro(distance, sc.nextDouble());
            default -> throw new IllegalArgumentException("Unknown transport type: " + type);
        };
    }

    public static void main(String[] args) {
        String input = """
                3
                BUS 15
                TRAIN 50
                METRO 10 1.5
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        Transport[] journeys = new Transport[n];
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            journeys[i] = createJourney(type, sc);
        }

        // Uniform processing: no type checks
        double total = 0;
        for (Transport t : journeys) {
            double fare = t.calculateFare();
            System.out.printf("%s: %.2f%n", t.getType(), fare);
            total = total + fare;
        }
        System.out.printf("Total: %.2f%n", total);
    }
}