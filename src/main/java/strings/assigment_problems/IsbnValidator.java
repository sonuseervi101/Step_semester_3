package strings.assigment_problems;

public class IsbnValidator {

    // Step 1: remove spaces and uppercase only the first 3 characters
    public static String normalizeCode(String raw) {
        String trimmed = raw.trim();

        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }

        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    // Step 2: validate and build the display line
    public static String validateAndFormat(String code) {
        // Check 1: exact length
        if (code.length() != 13) {
            return "Invalid: code must be exactly 13 characters";
        }

        // Check 2: first 3 characters must be letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // Check 3: remaining 10 characters must be digits
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        // All checks passed: break it into parts
        String publisher = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder result = new StringBuilder();
        result.append("[").append(publisher).append("] ");
        result.append("YEAR: ").append(year);
        result.append(" | CATALOG: ").append(catalog);

        return result.toString();
    }

    public static void main(String[] args) {
        String[] inputs = {"  pen2026004251  ", "12N2026004251"};

        for (String raw : inputs) {
            String normalized = normalizeCode(raw);
            System.out.println("Input: \"" + raw + "\" -> " + validateAndFormat(normalized));
        }
    }
}