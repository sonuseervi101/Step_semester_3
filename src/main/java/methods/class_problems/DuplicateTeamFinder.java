package methods.class_problems;

import java.util.Arrays;

public class DuplicateTeamFinder {

    // Returns the first duplicate team name, scanning in order
    static String findDuplicateTeam(String[] teamNames) {
        for (int i = 0; i < teamNames.length; i++) {
            for (int j = i + 1; j < teamNames.length; j++) {
                if (teamNames[i].equals(teamNames[j])) {
                    return "Duplicate Found: " + teamNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    public static void main(String[] args) {
        String[] teams1 = {"ByteForce", "CodeCrafters", "ByteForce"};
        System.out.println(Arrays.toString(teams1) + " -> " + findDuplicateTeam(teams1));

        String[] teams2 = {"ByteForce", "CodeCrafters", "NullPointers"};
        System.out.println(Arrays.toString(teams2) + " -> " + findDuplicateTeam(teams2));
    }
}