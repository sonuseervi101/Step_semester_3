package abstraction.class_problems;

import java.util.Scanner;

public class TravelBookingCommonFee {

    static abstract class Booking {
        private static final double BOOKING_FEE = 50;   // the ONE line to change if the fee changes

        protected final double distanceKm;

        Booking(double distanceKm) {
            this.distanceKm = distanceKm;
        }

        abstract String getMode();

        abstract double calculateBaseFare();   // each mode's own fare (without the fee)

        // final: subclasses cannot override this, so the fee can never be skipped or changed
        final double calculateTotal() {
            return calculateBaseFare() + BOOKING_FEE;
        }
    }

    static class BusBooking extends Booking {
        BusBooking(double km) { super(km); }

        @Override
        String getMode() { return "BUS"; }

        @Override
        double calculateBaseFare() { return 2.0 * distanceKm; }
    }

    static class TrainBooking extends Booking {
        TrainBooking(double km) { super(km); }

        @Override
        String getMode() { return "TRAIN"; }

        @Override
        double calculateBaseFare() { return 1.5 * distanceKm; }
    }

    static class FlightBooking extends Booking {
        FlightBooking(double km) { super(km); }

        @Override
        String getMode() { return "FLIGHT"; }

        @Override
        double calculateBaseFare() { return 2500 + 4.0 * distanceKm; }
    }

    // Factory: the only place that looks at the mode text
    static Booking createBooking(String mode, double km) {
        return switch (mode) {
            case "BUS" -> new BusBooking(km);
            case "TRAIN" -> new TrainBooking(km);
            case "FLIGHT" -> new FlightBooking(km);
            default -> throw new IllegalArgumentException("Unknown travel mode: " + mode);
        };
    }

    public static void main(String[] args) {
        String input = """
                3
                BUS 200
                TRAIN 300
                FLIGHT 500
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        Booking[] bookings = new Booking[n];
        for (int i = 0; i < n; i++) {
            String mode = sc.next();
            double km = sc.nextDouble();
            bookings[i] = createBooking(mode, km);
        }

        for (Booking b : bookings) {
            System.out.printf("%s: %.2f%n", b.getMode(), b.calculateTotal());
        }
    }
}