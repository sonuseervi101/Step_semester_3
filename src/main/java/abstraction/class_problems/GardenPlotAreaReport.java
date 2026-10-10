package abstraction.class_problems;

import java.util.Scanner;

public class GardenPlotAreaReport {

    // Abstract: shows WHAT every plot can do (give its area), hides HOW
    static abstract class Plot {
        protected final String owner;

        Plot(String owner) {
            this.owner = owner;
        }

        abstract String getShape();

        abstract double calculateArea();

        // Shared reporting line: works for any shape, even ones added later
        String reportLine() {
            return String.format("%s (%s): %.2f", owner, getShape(), calculateArea());
        }
    }

    static class CirclePlot extends Plot {
        private final double radius;

        CirclePlot(String owner, double radius) {
            super(owner);
            this.radius = radius;
        }

        @Override
        String getShape() { return "CIRCLE"; }

        @Override
        double calculateArea() { return Math.PI * radius * radius; }
    }

    static class RectanglePlot extends Plot {
        private final double length;
        private final double width;

        RectanglePlot(String owner, double length, double width) {
            super(owner);
            this.length = length;
            this.width = width;
        }

        @Override
        String getShape() { return "RECTANGLE"; }

        @Override
        double calculateArea() { return length * width; }
    }

    static class TrianglePlot extends Plot {
        private final double base;
        private final double height;

        TrianglePlot(String owner, double base, double height) {
            super(owner);
            this.base = base;
            this.height = height;
        }

        @Override
        String getShape() { return "TRIANGLE"; }

        @Override
        double calculateArea() { return 0.5 * base * height; }
    }

    // Factory: reads the right measurements for each shape
    static Plot createPlot(String shape, Scanner sc) {
        String owner = sc.next();
        return switch (shape) {
            case "CIRCLE" -> new CirclePlot(owner, sc.nextDouble());
            case "RECTANGLE" -> new RectanglePlot(owner, sc.nextDouble(), sc.nextDouble());
            case "TRIANGLE" -> new TrianglePlot(owner, sc.nextDouble(), sc.nextDouble());
            default -> throw new IllegalArgumentException("Unknown shape: " + shape);
        };
    }

    public static void main(String[] args) {
        String input = """
                3
                CIRCLE Asha 5
                RECTANGLE Ravi 4 6
                TRIANGLE Neha 10 3
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        Plot[] plots = new Plot[n];
        for (int i = 0; i < n; i++) {
            plots[i] = createPlot(sc.next(), sc);
        }

        // Report: never needs to change when a new shape is added
        double total = 0;
        for (Plot p : plots) {
            System.out.println(p.reportLine());
            total = total + p.calculateArea();
        }
        System.out.printf("Total Area: %.2f%n", total);
    }
}