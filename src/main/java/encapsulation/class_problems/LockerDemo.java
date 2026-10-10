package encapsulation.class_problems;

class Locker {
    private final int lockerNumber;   // fixed at creation
    private String code;              // write-only: there is NO getter for this

    public Locker(int lockerNumber, String initialCode) {
        this.lockerNumber = lockerNumber;
        this.code = initialCode;
    }

    // Changes the code only if the current code is entered correctly
    public boolean changeCode(String currentCode, String newCode) {
        if (!code.equals(currentCode)) {
            return false;   // wrong code: do nothing else
        }
        code = newCode;
        return true;
    }

    public int getLockerNumber() {
        return lockerNumber;
    }
}

public class LockerDemo {
    public static void main(String[] args) {
        Locker l = new Locker(101, "1234");
        System.out.println("Locker " + l.getLockerNumber() + " created");

        boolean first = l.changeCode("1234", "5678");
        System.out.println("changeCode(\"1234\", \"5678\") -> " + (first ? "success" : "rejected"));

        boolean second = l.changeCode("0000", "9999");
        System.out.println("changeCode(\"0000\", \"9999\") -> " + (second ? "success" : "rejected"));

        // Proof the code is still "5678" without reading it: using 5678 as the current code works
        boolean third = l.changeCode("5678", "4321");
        System.out.println("changeCode(\"5678\", \"4321\") -> " + (third ? "success (code was still 5678)" : "rejected"));
    }
}