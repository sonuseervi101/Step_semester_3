package strings.class_problems;

public class FileExtensionValidator {

    // Checks if the file has an accepted extension (pdf, docx, zip)
    public static String validateFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');

        // No dot, or dot at the very end means there is no extension
        if (dotIndex == -1 || dotIndex == filename.length() - 1) {
            return "Rejected — invalid file type";
        }

        String extension = filename.substring(dotIndex + 1);

        if (extension.equalsIgnoreCase("pdf")
                || extension.equalsIgnoreCase("docx")
                || extension.equalsIgnoreCase("zip")) {
            return "Accepted";
        }
        return "Rejected — invalid file type";
    }

    public static void main(String[] args) {
        String[] files = {"Assignment1.PDF", "notes.txt"};

        for (String file : files) {
            System.out.println("\"" + file + "\" -> " + validateFileExtension(file));
        }
    }
}