package basics.class_problems;

import java.util.Random;

public class RockPaperScissors {

    // Decides who wins one round
    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "Draw";
        }
        if (playerMove.equals("Rock") && computerMove.equals("Scissors")) {
            return "Player Wins";
        }
        if (playerMove.equals("Paper") && computerMove.equals("Rock")) {
            return "Player Wins";
        }
        if (playerMove.equals("Scissors") && computerMove.equals("Paper")) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    public static void main(String[] args) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};
        String[] computerMoves = new String[5];
        String[] results = new String[5];

        Random random = new Random();
        int wins = 0, losses = 0, draws = 0;

        // Play 5 rounds
        for (int i = 0; i < 5; i++) {
            computerMoves[i] = moves[random.nextInt(3)];
            results[i] = playRound(playerMoves[i], computerMoves[i]);

            if (results[i].equals("Player Wins")) {
                wins++;
            } else if (results[i].equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        // Print the summary table
        System.out.println("Round\tPlayer\t\tComputer\tResult");
        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + "\t" + playerMoves[i] + "\t\t" + computerMoves[i] + "\t\t" + results[i]);
        }

        // Print the final stats
        double winPercentage = wins * 100.0 / 5;
        System.out.println();
        System.out.println("Wins: " + wins + " | Losses: " + losses + " | Draws: " + draws);
        System.out.println("Win % = " + winPercentage + "%");
    }
}