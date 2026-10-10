package oop.class_problems;

// The wallet class: balance is private and protected by rules
class MessWallet {
    private double balance;   // no one outside this class can touch it directly

    public MessWallet(double openingBalance) {
        if (openingBalance < 0) {
            System.out.println("Warning: opening balance cannot be negative. Starting at 0.");
            balance = 0;
        } else {
            balance = openingBalance;
        }
    }

    public void topUp(double amount) {
        if (amount <= 0) {
            System.out.println("Top-up rejected: amount must be greater than 0");
            return;
        }
        balance = balance + amount;
    }

    public void deduct(double amount) {
        if (amount > balance) {
            System.out.println("Deduct rejected: insufficient balance");
            return;
        }
        balance = balance - amount;
    }

    // Read-only access: there is no setBalance() method
    public double getBalance() {
        return balance;
    }
}

public class HostelMessWallet {
    public static void main(String[] args) {
        MessWallet wallet = new MessWallet(500);

        wallet.topUp(200);
        System.out.println("Balance after top-up: " + wallet.getBalance());

        wallet.deduct(1000);
        System.out.println("Final balance: " + wallet.getBalance());

        // wallet.balance = 99999;   // would NOT compile: balance is private
    }
}