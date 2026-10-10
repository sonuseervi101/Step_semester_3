package abstraction.assigment_problems;

import java.util.Scanner;

public class ApplianceEnergyReport {

    static final double COST_PER_UNIT = 8;

    // Interface: an ability that only some appliances have
    interface SaverMode {
        double SAVER_REDUCTION = 0.25;   // uses 25% less energy

        default double applySaver(double units) {
            return units * (1 - SAVER_REDUCTION);
        }
    }

    // Abstract class: what EVERY appliance has, including the shared units formula
    static abstract class Appliance {
        protected final double hours;
        protected final boolean saverRequested;

        Appliance(double hours, boolean saverRequested) {
            this.hours = hours;
            this.saverRequested = saverRequested;
        }

        abstract String getName();

        abstract double getPowerWatts();   // the only thing that differs per appliance

        // Shared: units (kWh) = power x hours / 1000
        final double calculateUnits() {
            return getPowerWatts() * hours / 1000;
        }
    }

    static class Fridge extends Appliance {   // no saver mode
        Fridge(double hours, boolean saver) { super(hours, saver); }

        @Override
        String getName() { return "FRIDGE"; }

        @Override
        double getPowerWatts() { return 150; }
    }

    static class AirConditioner extends Appliance implements SaverMode {
        AirConditioner(double hours, boolean saver) { super(hours, saver); }

        @Override
        String getName() { return "AC"; }

        @Override
        double getPowerWatts() { return 1500; }
    }

    static class Tv extends Appliance {   // no saver mode
        Tv(double hours, boolean saver) { super(hours, saver); }

        @Override
        String getName() { return "TV"; }

        @Override
        double getPowerWatts() { return 100; }
    }

    static class WashingMachine extends Appliance implements SaverMode {
        WashingMachine(double hours, boolean saver) { super(hours, saver); }

        @Override
        String getName() { return "WASHER"; }

        @Override
        double getPowerWatts() { return 500; }
    }

    // Factory: the only place that looks at the appliance text
    static Appliance createAppliance(String name, double hours, boolean saver) {
        return switch (name) {
            case "FRIDGE" -> new Fridge(hours, saver);
            case "AC" -> new AirConditioner(hours, saver);
            case "TV" -> new Tv(hours, saver);
            case "WASHER" -> new WashingMachine(hours, saver);
            default -> throw new IllegalArgumentException("Unknown appliance: " + name);
        };
    }

    public static void main(String[] args) {
        String input = """
                4
                FRIDGE 24
                AC 8 SAVER
                TV 5
                WASHER 2 SAVER
                """;
        Scanner sc = new Scanner(input);

        int n = Integer.parseInt(sc.nextLine().trim());
        Appliance[] appliances = new Appliance[n];
        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().trim().split("\\s+");
            String name = parts[0];
            double hours = Double.parseDouble(parts[1]);
            boolean saver = parts.length == 3 && parts[2].equals("SAVER");   // SAVER is optional
            appliances[i] = createAppliance(name, hours, saver);
        }

        double totalCost = 0;
        for (Appliance a : appliances) {
            double units = a.calculateUnits();

            if (a.saverRequested) {
                // Ask "does this appliance support saver mode?", not "is it an AC or a washer?"
                if (a instanceof SaverMode saverAppliance) {
                    units = saverAppliance.applySaver(units);
                } else {
                    System.out.println(a.getName() + ": saver mode not supported");
                    continue;   // reported instead of calculating a cost
                }
            }

            double cost = units * COST_PER_UNIT;
            System.out.printf("%s: Units=%.2f Cost=%.2f%n", a.getName(), units, cost);
            totalCost = totalCost + cost;
        }
        System.out.printf("Total Cost: %.2f%n", totalCost);
    }
}