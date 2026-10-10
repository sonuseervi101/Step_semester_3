package methods.assigment_problems;

import java.util.Arrays;

public class Player implements Comparable<Player> {

    // Draft thresholds (chosen to match the sample output)
    static final int EXPERIENCED_MATCHES = 10;
    static final int NEWCOMER_MIN_MATCHES = 5;

    // Encapsulation: private fields, read through getters
    private String name;
    private int matchesPlayed;
    private double battingAverage;
    private boolean injured;

    public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.battingAverage = battingAverage;
        this.injured = injured;
    }

    public String getName() {
        return name;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public boolean isInjured() {
        return injured;
    }

    // Fantasy points used for ranking
    public double getFantasyPoints() {
        return battingAverage;
    }

    // Overload 1: established players qualify on experience alone
    static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= EXPERIENCED_MATCHES;
    }

    // Overload 2: newer players need some experience AND must be fit
    static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= NEWCOMER_MIN_MATCHES && !injured;
    }

    // Higher fantasy points come first (descending order)
    @Override
    public int compareTo(Player other) {
        return Double.compare(other.getFantasyPoints(), this.getFantasyPoints());
    }

    static String draftAndRank(Player[] players) {
        // Step 1: count draftable players
        int count = 0;
        for (Player p : players) {
            if (isDraftable(p.getMatchesPlayed()) || isDraftable(p.getMatchesPlayed(), p.isInjured())) {
                count++;
            }
        }

        // Step 2: copy them into a new array
        Player[] draftable = new Player[count];
        int index = 0;
        for (Player p : players) {
            if (isDraftable(p.getMatchesPlayed()) || isDraftable(p.getMatchesPlayed(), p.isInjured())) {
                draftable[index] = p;
                index++;
            }
        }

        // Step 3: rank them - Arrays.sort uses our compareTo automatically
        Arrays.sort(draftable);

        // Step 4: build the output line
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < draftable.length; i++) {
            result.append(i + 1).append(". ").append(draftable[i].getName());
            if (i < draftable.length - 1) {
                result.append(" | ");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Player[] players = {
                new Player("Virat", 15, 48.0, false),
                new Player("Rahul", 7, 55.0, false),
                new Player("Sameer", 3, 60.0, false),
                new Player("Dev", 12, 20.0, true)
        };

        System.out.println(draftAndRank(players));
    }
}