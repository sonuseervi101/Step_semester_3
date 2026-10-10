package basics.assigment_problems;

public class TrafficSignalStreak {

    // Finds the longest run of the same color in the signal log
    public static void findLongestStreak(String signalLog) {
        if (signalLog.length() == 0) {
            System.out.println("Signal log is empty");
            return;
        }

        char longestColor = signalLog.charAt(0);
        int longestLength = 1;

        int currentLength = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == signalLog.charAt(i - 1)) {
                currentLength++;          // same color as before: streak continues
            } else {
                currentLength = 1;        // color changed: start a new streak
            }

            if (currentLength > longestLength) {
                longestLength = currentLength;
                longestColor = signalLog.charAt(i);
            }
        }

        System.out.println("\"" + signalLog + "\" -> Longest Streak: '" + longestColor
                + "' repeated " + longestLength + " times");
    }

    public static void main(String[] args) {
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");
    }
}