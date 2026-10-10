package polymorphism.class_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExamQuestionGrader {

    // Base class: every question has the answers and points, and grades itself
    static abstract class Question {
        protected final String correctAnswer;
        protected final String studentAnswer;
        protected final int points;

        Question(String correctAnswer, String studentAnswer, int points) {
            this.correctAnswer = correctAnswer;
            this.studentAnswer = studentAnswer;
            this.points = points;
        }

        abstract String getType();

        abstract double grade();
    }

    // Middle class: shared "exact match" grading for MCQ and True/False
    static abstract class ExactMatchQuestion extends Question {
        ExactMatchQuestion(String correctAnswer, String studentAnswer, int points) {
            super(correctAnswer, studentAnswer, points);
        }

        @Override
        double grade() {
            return studentAnswer.equals(correctAnswer) ? points : 0;
        }
    }

    static class McqQuestion extends ExactMatchQuestion {
        McqQuestion(String correct, String student, int points) { super(correct, student, points); }

        @Override
        String getType() { return "MCQ"; }
        // grade() is inherited from ExactMatchQuestion
    }

    static class TrueFalseQuestion extends ExactMatchQuestion {
        TrueFalseQuestion(String correct, String student, int points) { super(correct, student, points); }

        @Override
        String getType() { return "TF"; }
        // grade() is inherited from ExactMatchQuestion
    }

    static class EssayQuestion extends Question {
        EssayQuestion(String correct, String student, int points) { super(correct, student, points); }

        @Override
        String getType() { return "ESSAY"; }

        @Override
        double grade() {
            String[] keywords = correctAnswer.split(",");
            String answerLower = studentAnswer.toLowerCase();

            int matches = 0;
            for (String keyword : keywords) {
                if (answerLower.contains(keyword.trim().toLowerCase())) {
                    matches++;
                }
            }

            if (matches >= 2) {
                return points * 0.75;
            } else if (matches == 1) {
                return points * 0.50;
            }
            return 0;
        }
    }

    // Splits a line into tokens, keeping "quoted text" together as one token
    static List<String> tokenize(String line) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while (i < line.length()) {
            char ch = line.charAt(i);
            if (ch == ' ') {
                i++;                                        // skip spaces between tokens
            } else if (ch == '"') {
                int end = line.indexOf('"', i + 1);         // find the closing quote
                tokens.add(line.substring(i + 1, end));
                i = end + 1;
            } else {
                int end = line.indexOf(' ', i);             // plain word: read until the next space
                if (end == -1) {
                    end = line.length();
                }
                tokens.add(line.substring(i, end));
                i = end;
            }
        }
        return tokens;
    }

    // Factory: the only place that looks at the type text
    static Question createQuestion(List<String> t) {
        String type = t.get(0);
        // t.get(1) is the question text, which isn't needed for grading
        String correct = t.get(2);
        String student = t.get(3);
        int points = Integer.parseInt(t.get(4));

        return switch (type) {
            case "MCQ" -> new McqQuestion(correct, student, points);
            case "TF" -> new TrueFalseQuestion(correct, student, points);
            case "ESSAY" -> new EssayQuestion(correct, student, points);
            default -> throw new IllegalArgumentException("Unknown question type: " + type);
        };
    }

    public static void main(String[] args) {
        String input = """
                4
                MCQ "What is the capital of France?" "Paris" "Paris" 10
                TF "The Earth is flat?" "False" "True" 5
                ESSAY "Name two primary OOP principles." "Inheritance, Polymorphism, Encapsulation" "Polymorphism is one." 20
                ESSAY "Describe abstraction and composition." "Abstraction, Composition" "I talked about abstraction." 15
                """;
        Scanner sc = new Scanner(input);

        int n = Integer.parseInt(sc.nextLine().trim());
        Question[] questions = new Question[n];
        for (int i = 0; i < n; i++) {
            questions[i] = createQuestion(tokenize(sc.nextLine().trim()));
        }

        // Uniform processing: no type checks
        double total = 0;
        for (Question q : questions) {
            double score = q.grade();
            System.out.printf("%s: %.2f%n", q.getType(), score);
            total = total + score;
        }
        System.out.printf("Total Score: %.2f%n", total);
    }
}