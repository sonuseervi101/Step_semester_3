package encapsulation.class_problems;

class Scorecard {
    private final boolean[] results;   // private: never handed out
    private int answersRecorded;       // where the next answer goes

    public Scorecard(int totalQuestions) {
        results = new boolean[totalQuestions];   // size fixed at creation
        answersRecorded = 0;
    }

    public void recordAnswer(boolean isCorrect) {
        if (answersRecorded >= results.length) {
            System.out.println("Rejected: all " + results.length + " answers already recorded");
            return;
        }
        results[answersRecorded] = isCorrect;
        answersRecorded++;
    }

    // Only the computed score is revealed, never the array
    public int getScore() {
        int score = 0;
        for (int i = 0; i < answersRecorded; i++) {
            if (results[i]) {
                score++;
            }
        }
        return score;
    }
}

public class ScorecardDemo {
    public static void main(String[] args) {
        Scorecard sc = new Scorecard(4);

        sc.recordAnswer(true);
        sc.recordAnswer(true);
        sc.recordAnswer(false);
        sc.recordAnswer(true);
        System.out.println("Score: " + sc.getScore());

        sc.recordAnswer(true);   // a 5th answer on a 4-question quiz
        System.out.println("Score after extra attempt: " + sc.getScore());
    }
}