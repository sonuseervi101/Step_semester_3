package revision.class_problems;

public class LeapYearDetector {

    static boolean isLeapYear(int year) {
        // Divisible by 4 AND not by 100, OR divisible by 400
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void main(String[] args) {
        int[] years = {2024, 1900, 2000};

        for (int year : years) {
            if (isLeapYear(year)) {
                System.out.println("Leap year");
            } else {
                System.out.println("Not a leap year");
            }
        }
    }
}