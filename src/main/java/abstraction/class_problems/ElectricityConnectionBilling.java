package abstraction.class_problems;

import java.util.Scanner;

public class ElectricityConnectionBilling {

    // Abstract base: common "units" property + abstract billing method
    static abstract class Connection {
        protected final int units;

        Connection(int units) {
            this.units = units;
        }

        abstract String getType();

        abstract double calculateBill();
    }

    static class HomeConnection extends Connection {
        HomeConnection(int units) { super(units); }

        @Override
        String getType() { return "HOME"; }

        @Override
        double calculateBill() {
            if (units <= 100) {
                return 5.0 * units;
            }
            return 5.0 * 100 + 7.0 * (units - 100);   // first 100 at 5, the rest at 7
        }
    }

    static class ShopConnection extends Connection {
        ShopConnection(int units) { super(units); }

        @Override
        String getType() { return "SHOP"; }

        @Override
        double calculateBill() { return 8.0 * units + 100; }
    }

    static class FactoryConnection extends Connection {
        FactoryConnection(int units) { super(units); }

        @Override
        String getType() { return "FACTORY"; }

        @Override
        double calculateBill() { return Math.max(6.0 * units, 1000); }   // minimum bill 1000
    }

    // Factory method: the only place that looks at the type text
    static Connection createConnection(String type, int units) {
        return switch (type) {
            case "HOME" -> new HomeConnection(units);
            case "SHOP" -> new ShopConnection(units);
            case "FACTORY" -> new FactoryConnection(units);
            default -> throw new IllegalArgumentException("Unknown connection type: " + type);
        };
    }

    public static void main(String[] args) {
        String input = """
                3
                HOME 150
                SHOP 90
                FACTORY 120
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        Connection[] connections = new Connection[n];
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int units = sc.nextInt();
            connections[i] = createConnection(type, units);
        }

        double total = 0;
        for (Connection c : connections) {
            double bill = c.calculateBill();
            System.out.printf("%s: %.2f%n", c.getType(), bill);
            total = total + bill;
        }
        System.out.printf("Total: %.2f%n", total);
    }
}