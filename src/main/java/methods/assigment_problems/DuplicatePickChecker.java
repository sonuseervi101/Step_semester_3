package methods.assigment_problems;

import java.util.Arrays;

public class DuplicatePickChecker {

    // Returns the first repeated player name, scanning in order
    static String findDuplicatePick(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            for (int j = i + 1; j < playerNames.length; j++) {
                if (playerNames[i].equals(playerNames[j])) {
                    return "Duplicate Found: " + playerNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    public static void main(String[] args) {
        String[] lineup1 = {"Kohli", "Bumrah", "Kohli", "Rohit"};
        System.out.println(Arrays.toString(lineup1) + " -> " + findDuplicatePick(lineup1));

        String[] lineup2 = {"Kohli", "Bumrah", "Rohit"};
        System.out.println(Arrays.toString(lineup2) + " -> " + findDuplicatePick(lineup2));
    }
}
