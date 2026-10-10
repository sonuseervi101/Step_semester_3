package basics.assigment_problems;

public class DuplicateSeatChecker {

    // Finds and prints duplicate seat numbers using only arrays and loops
    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean foundDuplicate = false;

        for (int i = 0; i < seatNumbers.length; i++) {

            // Skip this seat if we already checked the same number earlier
            boolean alreadyChecked = false;
            for (int k = 0; k < i; k++) {
                if (seatNumbers[k] == seatNumbers[i]) {
                    alreadyChecked = true;
                    break;
                }
            }
            if (alreadyChecked) {
                continue;
            }

            // Compare with every seat after it
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    foundDuplicate = true;
                    break;
                }
            }
        }

        if (!foundDuplicate) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        int[] hall1 = {101, 102, 103, 102, 105};
        int[] hall2 = {101, 102, 103, 104, 105};

        System.out.println("Hall 1:");
        checkDuplicateSeats(hall1);

        System.out.println("\nHall 2:");
        checkDuplicateSeats(hall2);
    }
}