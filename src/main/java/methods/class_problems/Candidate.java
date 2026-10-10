package methods.class_problems;

import java.util.Arrays;

public class Candidate implements Comparable<Candidate> {

    // Eligibility thresholds (chosen to match the sample output)
    static final double CGPA_ONLY_CUTOFF = 7.5;
    static final double BORDERLINE_CGPA_CUTOFF = 6.5;
    static final int CODING_CUTOFF = 60;

    // Encapsulation: fields are private, read only through getters
    private String name;
    private double cgpa;
    private int codingScore;

    public Candidate(String name, double cgpa, int codingScore) {
        this.name = name;
        this.cgpa = cgpa;
        this.codingScore = codingScore;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    public int getCodingScore() {
        return codingScore;
    }

    // Composite score used for ranking
    public double getCompositeScore() {
        return cgpa * 10 + codingScore * 0.5;
    }

    // Overload 1: quick CGPA-only filter
    static boolean isEligible(double cgpa) {
        return cgpa >= CGPA_ONLY_CUTOFF;
    }

    // Overload 2: combined check for borderline CGPA
    static boolean isEligible(double cgpa, int codingScore) {
        return cgpa >= BORDERLINE_CGPA_CUTOFF && codingScore >= CODING_CUTOFF;
    }

    // Higher composite score comes first (descending order)
    @Override
    public int compareTo(Candidate other) {
        return Double.compare(other.getCompositeScore(), this.getCompositeScore());
    }

    static String shortlistAndRank(Candidate[] candidates) {
        // Step 1: count how many are eligible
        int count = 0;
        for (Candidate c : candidates) {
            if (isEligible(c.getCgpa()) || isEligible(c.getCgpa(), c.getCodingScore())) {
                count++;
            }
        }

        // Step 2: copy the eligible ones into a new array
        Candidate[] shortlisted = new Candidate[count];
        int index = 0;
        for (Candidate c : candidates) {
            if (isEligible(c.getCgpa()) || isEligible(c.getCgpa(), c.getCodingScore())) {
                shortlisted[index] = c;
                index++;
            }
        }

        // Step 3: rank them - Arrays.sort uses our compareTo automatically
        Arrays.sort(shortlisted);

        // Step 4: build the output line
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < shortlisted.length; i++) {
            result.append(i + 1).append(". ")
                    .append(shortlisted[i].getName())
                    .append(" (").append(String.format("%.1f", shortlisted[i].getCompositeScore())).append(")");
            if (i < shortlisted.length - 1) {
                result.append(" | ");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Candidate[] candidates = {
                new Candidate("Aisha", 8.2, 40),
                new Candidate("Rohit", 6.8, 65),
                new Candidate("Meena", 6.0, 90),
                new Candidate("Karan", 7.5, 20)
        };

        System.out.println(shortlistAndRank(candidates));
    }
}