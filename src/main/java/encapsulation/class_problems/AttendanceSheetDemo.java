package encapsulation.class_problems;

class AttendanceSheet {
    private final String[] presentStudents;   // private: never returned
    private int presentCount;                 // how many names are stored so far

    public AttendanceSheet(int maxClassSize) {
        presentStudents = new String[maxClassSize];
        presentCount = 0;
    }

    public void markPresent(String name) {
        if (isPresent(name)) {
            return;   // already marked: don't count the same student twice
        }
        if (presentCount >= presentStudents.length) {
            System.out.println("Rejected: class is full");
            return;
        }
        presentStudents[presentCount] = name;
        presentCount++;
    }

    public int getPresentCount() {
        return presentCount;
    }

    // Yes/no lookup: loops through the private array internally
    public boolean isPresent(String name) {
        for (int i = 0; i < presentCount; i++) {
            if (presentStudents[i].equals(name)) {
                return true;
            }
        }
        return false;
    }
}

public class AttendanceSheetDemo {
    public static void main(String[] args) {
        AttendanceSheet sheet = new AttendanceSheet(30);

        sheet.markPresent("Ana");
        sheet.markPresent("Ben");
        sheet.markPresent("Ana");   // duplicate: ignored

        System.out.println("Present count: " + sheet.getPresentCount());
        System.out.println("isPresent(\"Ben\"): " + sheet.isPresent("Ben"));
        System.out.println("isPresent(\"Chen\"): " + sheet.isPresent("Chen"));
    }
}