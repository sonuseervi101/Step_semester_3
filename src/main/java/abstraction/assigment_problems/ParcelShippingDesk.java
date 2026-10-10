package abstraction.assigment_problems;

import java.util.Scanner;

public class ParcelShippingDesk {

    // Interface: an ABILITY that only some parcels have
    interface Insurable {
        double INSURANCE_RATE = 0.02;   // interface fields are automatically public, static and final

        double getDeclaredValue();

        // default method: shared implementation for every insurable parcel
        default double calculateInsurance() {
            return getDeclaredValue() * INSURANCE_RATE;
        }
    }

    // Abstract class: what EVERY parcel has and does
    static abstract class Parcel {
        protected final double weightKg;
        protected final double declaredValue;

        Parcel(double weightKg, double declaredValue) {
            this.weightKg = weightKg;
            this.declaredValue = declaredValue;
        }

        public double getDeclaredValue() {
            return declaredValue;
        }

        abstract String getType();

        abstract double calculateCharge();
    }

    static class StandardParcel extends Parcel {   // NOT insurable
        StandardParcel(double weight, double value) { super(weight, value); }

        @Override
        String getType() { return "STANDARD"; }

        @Override
        double calculateCharge() { return 40 + 10 * weightKg; }
    }

    static class ExpressParcel extends Parcel implements Insurable {
        ExpressParcel(double weight, double value) { super(weight, value); }

        @Override
        String getType() { return "EXPRESS"; }

        @Override
        double calculateCharge() { return 80 + 15 * weightKg; }
    }

    // Fragile = standard charge + handling fee, AND it is insurable
    static class FragileParcel extends StandardParcel implements Insurable {
        FragileParcel(double weight, double value) { super(weight, value); }

        @Override
        String getType() { return "FRAGILE"; }

        @Override
        double calculateCharge() { return super.calculateCharge() + 50; }   // reuse the standard charge
    }

    // Factory: the only place that looks at the type text
    static Parcel createParcel(String type, double weight, double value) {
        return switch (type) {
            case "STANDARD" -> new StandardParcel(weight, value);
            case "EXPRESS" -> new ExpressParcel(weight, value);
            case "FRAGILE" -> new FragileParcel(weight, value);
            default -> throw new IllegalArgumentException("Unknown parcel type: " + type);
        };
    }

    public static void main(String[] args) {
        String input = """
                3
                STANDARD 3 500
                EXPRESS 2 1000
                FRAGILE 4 2000
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        Parcel[] parcels = new Parcel[n];
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double weight = sc.nextDouble();
            double value = sc.nextDouble();
            parcels[i] = createParcel(type, weight, value);
        }

        double grandTotal = 0;
        for (Parcel p : parcels) {
            double charge = p.calculateCharge();

            // Ask about the ABILITY, not the specific type
            double insurance = 0;
            if (p instanceof Insurable insurable) {
                insurance = insurable.calculateInsurance();
            }

            double total = charge + insurance;
            System.out.printf("%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                    p.getType(), charge, insurance, total);
            grandTotal = grandTotal + total;
        }
        System.out.printf("Grand Total: %.2f%n", grandTotal);
    }
}