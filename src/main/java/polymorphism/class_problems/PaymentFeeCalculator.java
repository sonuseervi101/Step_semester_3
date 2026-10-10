package polymorphism.class_problems;

import java.util.Scanner;

public class PaymentFeeCalculator {

    // Base class: every payment has an amount and can calculate its final amount
    static abstract class Payment {
        protected final double amount;

        Payment(double amount) {
            this.amount = amount;
        }

        abstract String getType();

        abstract double calculateFinalAmount();   // each subclass gives its own rule
    }

    static class CardPayment extends Payment {
        CardPayment(double amount) { super(amount); }

        @Override
        String getType() { return "CARD"; }

        @Override
        double calculateFinalAmount() { return amount * 1.02; }   // 2% fee
    }

    static class WalletPayment extends Payment {
        WalletPayment(double amount) { super(amount); }

        @Override
        String getType() { return "WALLET"; }

        @Override
        double calculateFinalAmount() { return amount * 1.01; }   // 1% fee
    }

    static class BankTransferPayment extends Payment {
        BankTransferPayment(double amount) { super(amount); }

        @Override
        String getType() { return "BANKTRANSFER"; }

        @Override
        double calculateFinalAmount() { return amount; }          // no fee
    }

    // Factory: the ONLY place that looks at the type text
    static Payment createPayment(String type, double amount) {
        return switch (type) {
            case "CARD" -> new CardPayment(amount);
            case "WALLET" -> new WalletPayment(amount);
            case "BANKTRANSFER" -> new BankTransferPayment(amount);
            default -> throw new IllegalArgumentException("Unknown payment type: " + type);
        };
    }

    public static void main(String[] args) {
        String input = """
                3
                CARD 1000
                WALLET 500
                BANKTRANSFER 2000
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        Payment[] payments = new Payment[n];
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amount = sc.nextDouble();
            payments[i] = createPayment(type, amount);
        }

        // Uniform processing: no type checks, each object uses its own rule
        double total = 0;
        for (Payment p : payments) {
            double finalAmount = p.calculateFinalAmount();
            System.out.printf("%s: %.2f%n", p.getType(), finalAmount);
            total = total + finalAmount;
        }
        System.out.printf("Total: %.2f%n", total);
    }
}