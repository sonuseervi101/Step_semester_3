package strings.class_problems;

public class PhoneNumberMasker {

    // Returns a masked phone number like XXXXXX-3210, or an error message
    public static String maskPhoneNumber(String phone) {
        // Validation 1: must be exactly 10 characters
        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        // Validation 2: every character must be a digit
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        String lastFour = phone.substring(6);   // characters from index 6 to the end

        StringBuilder masked = new StringBuilder();
        masked.append("XXXXXX");
        masked.append(lastFour);
        masked.insert(6, "-");                  // put "-" at position 6, after the mask

        return masked.toString();
    }

    public static void main(String[] args) {
        String[] phones = {"9876543210", "98765"};

        for (String phone : phones) {
            System.out.println("\"" + phone + "\" -> " + maskPhoneNumber(phone));
        }
    }
}