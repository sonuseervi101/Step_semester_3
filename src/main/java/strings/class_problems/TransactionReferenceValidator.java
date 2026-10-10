package strings.class_problems;

public class TransactionReferenceValidator {

    // Step 1: remove spaces and uppercase only the first 3 characters
    public static String normalizeReference(String raw) {
        String trimmed = raw.trim();

        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }

        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    // Step 2: validate and build the display line
    public static String validateAndFormat(String reference) {
        // Check 1: exact length
        if (reference.length() != 14) {
            return "Invalid: reference must be exactly 14 characters";
        }

        // Check 2: first 3 characters must be letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // Check 3: remaining 11 characters must be digits
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        // All checks passed: break it into parts
        String bankCode = reference.substring(0, 3);
        String day = reference.substring(3, 5);
        String month = reference.substring(5, 7);
        String year = reference.substring(7, 9);
        String sequence = reference.substring(9, 14);

        StringBuilder result = new StringBuilder();
        result.append("[").append(bankCode).append("] ");
        result.append("DATE: ").append(day).append("/").append(month).append("/").append(year);
        result.append(" | SEQ: ").append(sequence);

        return result.toString();
    }

    public static void main(String[] args) {
        String[] inputs = {"  hdf03022600042  ", "12F03022600042"};

        for (String raw : inputs) {
            String normalized = normalizeReference(raw);
            System.out.println("Input: \"" + raw + "\" -> " + validateAndFormat(normalized));
        }
    }
}