package abstraction.class_problems;

import java.util.Scanner;

public class LibraryLateFineCounter {

    // Abstract base: every item has a title and days late, and MUST define its fine rule
    static abstract class LibraryItem {
        protected final String title;
        protected final int daysLate;

        LibraryItem(String title, int daysLate) {
            this.title = title;
            this.daysLate = daysLate;
        }

        abstract double calculateFine();
    }

    static class Book extends LibraryItem {
        Book(String title, int daysLate) { super(title, daysLate); }

        @Override
        double calculateFine() { return 2.0 * daysLate; }
    }

    static class Dvd extends LibraryItem {
        private static final double MAX_FINE = 50;

        Dvd(String title, int daysLate) { super(title, daysLate); }

        @Override
        double calculateFine() { return Math.min(5.0 * daysLate, MAX_FINE); }   // capped at 50
    }

    static class Magazine extends LibraryItem {
        Magazine(String title, int daysLate) { super(title, daysLate); }

        @Override
        double calculateFine() { return 1.0 * daysLate; }
    }

    // Factory: the only place that looks at the type text
    static LibraryItem createItem(String type, String title, int daysLate) {
        return switch (type) {
            case "BOOK" -> new Book(title, daysLate);
            case "DVD" -> new Dvd(title, daysLate);
            case "MAGAZINE" -> new Magazine(title, daysLate);
            default -> throw new IllegalArgumentException("Unknown item type: " + type);
        };
    }

    public static void main(String[] args) {
        String input = """
                3
                BOOK Algebra 4
                DVD Inception 12
                MAGAZINE Sports 3
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        LibraryItem[] items = new LibraryItem[n];
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String title = sc.next();
            int daysLate = sc.nextInt();
            items[i] = createItem(type, title, daysLate);
        }

        double total = 0;
        for (LibraryItem item : items) {
            double fine = item.calculateFine();
            System.out.printf("%s: %.2f%n", item.title, fine);
            total = total + fine;
        }
        System.out.printf("Total Fines: %.2f%n", total);
    }
}