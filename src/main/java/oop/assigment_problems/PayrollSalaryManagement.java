package oop.assigment_problems;

class PayrollAccount {
    private double basicSalary;   // cannot be set directly from outside
    private double bonus;         // cannot be set directly from outside

    public PayrollAccount(double openingBasicSalary) {
        if (openingBasicSalary < 0) {
            System.out.println("Warning: basic salary cannot be negative. Starting at 0.");
            basicSalary = 0;
        } else {
            basicSalary = openingBasicSalary;
        }
        bonus = 0;
    }

    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Bonus rejected: amount must be greater than 0");
            return;
        }
        bonus = bonus + amount;
        System.out.println("Bonus credited: Rs " + amount);
    }

    public void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Tax rejected: percent must be between 0 and 100");
            return;
        }
        basicSalary = basicSalary - (basicSalary * percent / 100);
        System.out.println("Tax deducted: " + String.format("%.0f", percent) + "%");
    }

    // Read-only access: no setter for basicSalary or bonus
    public double getNetSalary() {
        return basicSalary + bonus;
    }
}

public class PayrollSalaryManagement {
    public static void main(String[] args) {
        PayrollAccount account = new PayrollAccount(50000);

        account.creditBonus(5000);
        account.deductTax(10);

        System.out.println("Net salary: Rs " + account.getNetSalary());
    }
}