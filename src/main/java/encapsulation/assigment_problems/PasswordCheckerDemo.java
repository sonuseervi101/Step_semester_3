package encapsulation.assigment_problems;

class PasswordChecker {
    private final String password;   // private + final: never returned, never changed

    public PasswordChecker(String password) {
        this.password = password;
    }

    // Reveals only a rating, never the password itself
    public String getStrength() {
        int length = password.length();

        if (length < 6) {
            return "Weak";
        } else if (length < 10) {
            return "Medium";
        } else {
            return "Strong";
        }
    }

    // Extra simple check: does the password contain a digit?
    public boolean hasDigit() {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}

public class PasswordCheckerDemo {
    public static void main(String[] args) {
        String[] testPasswords = {"abcd", "abcdefgh", "abcdefghij", "secure2026pw"};

        for (String pw : testPasswords) {
            PasswordChecker pc = new PasswordChecker(pw);
            System.out.println(pw.length() + " characters -> " + pc.getStrength()
                    + " | contains digit: " + pc.hasDigit());
        }
    }
}