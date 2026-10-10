package abstraction.assigment_problems;

import java.util.Scanner;

public class MovieTicketCounter {

    // Abstract: a plain "ticket" with no seat type can never be sold
    static abstract class Ticket {
        private static final double CONVENIENCE_FEE = 20;   // written in ONE place only

        protected final int count;

        Ticket(int count) {
            this.count = count;
        }

        abstract String getSeatType();

        abstract double getPricePerTicket();   // the only thing that changes per seat type

        // The part that never changes: count x (price + fee)
        final double calculateAmount() {
            return count * (getPricePerTicket() + CONVENIENCE_FEE);
        }
    }

    static class RegularTicket extends Ticket {
        RegularTicket(int count) { super(count); }

        @Override
        String getSeatType() { return "REGULAR"; }

        @Override
        double getPricePerTicket() { return 150; }
    }

    static class PremiumTicket extends Ticket {
        PremiumTicket(int count) { super(count); }

        @Override
        String getSeatType() { return "PREMIUM"; }

        @Override
        double getPricePerTicket() { return 250; }
    }

    static class ReclinerTicket extends Ticket {
        ReclinerTicket(int count) { super(count); }

        @Override
        String getSeatType() { return "RECLINER"; }

        @Override
        double getPricePerTicket() { return 400; }
    }

    // Factory: the only place that looks at the seat text
    static Ticket createTicket(String seat, int count) {
        return switch (seat) {
            case "REGULAR" -> new RegularTicket(count);
            case "PREMIUM" -> new PremiumTicket(count);
            case "RECLINER" -> new ReclinerTicket(count);
            default -> throw new IllegalArgumentException("Unknown seat type: " + seat);
        };
    }

    public static void main(String[] args) {
        String input = """
                3
                REGULAR 3
                PREMIUM 2
                RECLINER 1
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        Ticket[] bookings = new Ticket[n];
        for (int i = 0; i < n; i++) {
            String seat = sc.next();
            int count = sc.nextInt();
            bookings[i] = createTicket(seat, count);
        }

        double total = 0;
        for (Ticket t : bookings) {
            double amount = t.calculateAmount();
            System.out.printf("%s: %.2f%n", t.getSeatType(), amount);
            total = total + amount;
        }
        System.out.printf("Total: %.2f%n", total);
    }
}
