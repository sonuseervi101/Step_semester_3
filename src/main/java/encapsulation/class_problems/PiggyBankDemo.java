package encapsulation.class_problems;

class PiggyBank {
    private final String id;   // final: fixed forever once the constructor sets it
    private double savings;    // private: only deposit() and withdraw() can change it

    public PiggyBank(String id) {
        this.id = id;
        this.savings = 0;      // a new piggy bank starts empty
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit rejected: amount must be greater than 0");
            return;
        }
        savings = savings + amount;
    }

    public void withdraw(double amount) {
        if (amount > savings) {
            System.out.println("Withdraw rejected: not enough savings");
            return;
        }
        savings = savings - amount;
    }

    // Read-only access: there is no setSavings()
    public double getSavings() {
        return savings;
    }

    public String getId() {
        return id;
    }
}

public class PiggyBankDemo {
    public static void main(String[] args) {
        PiggyBank pb = new PiggyBank("PB-1");
        System.out.println("Piggy bank " + pb.getId() + " created, savings = " + pb.getSavings());

        pb.deposit(100);
        System.out.println("deposit(100)  -> savings = " + pb.getSavings());

        pb.withdraw(30);
        System.out.println("withdraw(30)  -> savings = " + pb.getSavings());

        pb.withdraw(500);
        System.out.println("withdraw(500) -> savings = " + pb.getSavings());
    }
}