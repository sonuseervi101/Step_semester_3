package polymorphism.class_problems;

import java.time.LocalDate;
import java.util.Scanner;

public class LibraryDueDateCalculator {

    static final LocalDate CURRENT_DATE = LocalDate.of(2023, 10, 26);

    // Base class: shared title, shared due-date logic, but each type sets its own loan period
    static abstract class LibraryItem {
        protected final String title;

        LibraryItem(String title) {
            this.title = title;
        }

        abstract int getLoanDays();   // each subclass gives its own number

        // Shared (inherited) behaviour: works for every item type
        LocalDate getDueDate() {
            return CURRENT_DATE.plusDays(getLoanDays());
        }
    }

    static class Book extends LibraryItem {
        Book(String title) { super(title); }

        @Override
        int getLoanDays() { return 14; }
    }

    static class Dvd extends LibraryItem {
        Dvd(String title) { super(title); }

        @Override
        int getLoanDays() { return 7; }
    }

    static class Magazine extends LibraryItem {
        Magazine(String title) { super(title); }

        @Override
        int getLoanDays() { return 3; }
    }

    // Factory: the only place that looks at the type text
    static LibraryItem createItem(String type, String title) {
        return switch (type) {
            case "BOOK" -> new Book(title);
            case "DVD" -> new Dvd(title);
            case "MAGAZINE" -> new Magazine(title);
            default -> throw new IllegalArgumentException("Unknown item type: " + type);
        };
    }

    public static void main(String[] args) {
        String input = """
                3
                BOOK "1984"
                DVD "The Matrix"
                MAGAZINE "Forbes Issue 500"
                """;
        Scanner sc = new Scanner(input);

        int n = Integer.parseInt(sc.nextLine().trim());
        LibraryItem[] items = new LibraryItem[n];

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String type = line.substring(0, line.indexOf(' '));                          // text before the first space
            String title = line.substring(line.indexOf('"') + 1, line.lastIndexOf('"')); // text between the quotes
            items[i] = createItem(type, title);
        }

        // Uniform processing: no type checks
        for (LibraryItem item : items) {
            System.out.println(item.title + ": " + item.getDueDate());
        }
    }
}