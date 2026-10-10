package strings.assigment_problems;

public class AtmPinValidator {

    // Checks only that the PIN has exactly 4 characters
    public static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        System.out.print("\"482\" -> ");
        checkPinLength("482");

        System.out.print("\"4820\" -> ");
        checkPinLength("4820");
    }
}