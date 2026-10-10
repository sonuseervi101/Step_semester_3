package abstraction.assigment_problems;

import java.util.Scanner;

public class CityCabFareMeter {

    // Interface: an ability that only some cabs offer
    interface NightService {
        double NIGHT_SURCHARGE = 0.20;   // +20%

        default double applyNightSurcharge(double fare) {
            return fare * (1 + NIGHT_SURCHARGE);
        }
    }

    // Abstract class: what EVERY cab trip has, including the shared minimum-fare rule
    static abstract class CabTrip {
        private static final double MINIMUM_FARE = 100;   // one rule for all cabs, written once

        protected final double km;
        protected final boolean isNight;

        CabTrip(double km, boolean isNight) {
            this.km = km;
            this.isNight = isNight;
        }

        abstract String getCabType();

        abstract double getRatePerKm();   // the only thing that differs per cab

        // Shared: distance x rate, never less than the minimum
        final double calculateBaseFare() {
            return Math.max(km * getRatePerKm(), MINIMUM_FARE);
        }
    }

    static class MiniCab extends CabTrip {   // no night service
        MiniCab(double km, boolean night) { super(km, night); }

        @Override
        String getCabType() { return "MINI"; }

        @Override
        double getRatePerKm() { return 10; }
    }

    static class SedanCab extends CabTrip implements NightService {
        SedanCab(double km, boolean night) { super(km, night); }

        @Override
        String getCabType() { return "SEDAN"; }

        @Override
        double getRatePerKm() { return 14; }
    }

    static class SuvCab extends CabTrip implements NightService {
        SuvCab(double km, boolean night) { super(km, night); }

        @Override
        String getCabType() { return "SUV"; }

        @Override
        double getRatePerKm() { return 18; }
    }

    // Factory: the only place that looks at the cab text
    static CabTrip createTrip(String cab, double km, boolean isNight) {
        return switch (cab) {
            case "MINI" -> new MiniCab(km, isNight);
            case "SEDAN" -> new SedanCab(km, isNight);
            case "SUV" -> new SuvCab(km, isNight);
            default -> throw new IllegalArgumentException("Unknown cab type: " + cab);
        };
    }

    public static void main(String[] args) {
        String input = """
                4
                MINI 8 DAY
                SEDAN 10 NIGHT
                SUV 20 DAY
                MINI 5 NIGHT
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        CabTrip[] trips = new CabTrip[n];
        for (int i = 0; i < n; i++) {
            String cab = sc.next();
            double km = sc.nextDouble();
            boolean isNight = sc.next().equals("NIGHT");
            trips[i] = createTrip(cab, km, isNight);
        }

        double total = 0;
        for (CabTrip trip : trips) {
            double fare = trip.calculateBaseFare();

            if (trip.isNight) {
                // Ask "can this cab do night service?", not "is this a SEDAN or SUV?"
                if (trip instanceof NightService nightCab) {
                    fare = nightCab.applyNightSurcharge(fare);
                } else {
                    System.out.println(trip.getCabType() + ": night service not available");
                    continue;   // rejected: not added to the total
                }
            }

            System.out.printf("%s: %.2f%n", trip.getCabType(), fare);
            total = total + fare;
        }
        System.out.printf("Total: %.2f%n", total);
    }
}