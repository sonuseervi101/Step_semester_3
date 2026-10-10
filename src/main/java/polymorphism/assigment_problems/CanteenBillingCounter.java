package polymorphism.assigment_problems;

import java.util.Scanner;

public class CanteenBillingCounter {

    // Base class: every customer has a bill and calculates their own final amount
    static abstract class Customer {
        protected final double billAmount;

        Customer(double billAmount) {
            this.billAmount = billAmount;
        }

        abstract String getType();

        abstract double calculateFinalAmount();
    }

    static class StudentCustomer extends Customer {
        StudentCustomer(double amount) { super(amount); }

        @Override
        String getType() { return "STUDENT"; }

        @Override
        double calculateFinalAmount() { return billAmount * 0.90; }   // 10% discount
    }

    static class StaffCustomer extends Customer {
        StaffCustomer(double amount) { super(amount); }

        @Override
        String getType() { return "STAFF"; }

        @Override
        double calculateFinalAmount() { return billAmount * 0.95; }   // 5% discount
    }

    static class GuestCustomer extends Customer {
        GuestCustomer(double amount) { super(amount); }

        @Override
        String getType() { return "GUEST"; }

        @Override
        double calculateFinalAmount() { return billAmount + 10; }     // Rs 10 service charge
    }

    // Factory: the only place that looks at the type text
    static Customer createCustomer(String type, double amount) {
        return switch (type) {
            case "STUDENT" -> new StudentCustomer(amount);
            case "STAFF" -> new StaffCustomer(amount);
            case "GUEST" -> new GuestCustomer(amount);
            default -> throw new IllegalArgumentException("Unknown customer type: " + type);
        };
    }

    public static void main(String[] args) {
        String input = """
                3
                STUDENT 200
                STAFF 300
                GUEST 150
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        Customer[] bills = new Customer[n];
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amount = sc.nextDouble();
            bills[i] = createCustomer(type, amount);
        }

        // Uniform processing: no type checks
        double total = 0;
        for (Customer c : bills) {
            double finalAmount = c.calculateFinalAmount();
            System.out.printf("%s: %.2f%n", c.getType(), finalAmount);
            total = total + finalAmount;
        }
        System.out.printf("Total: %.2f%n", total);
    }
}