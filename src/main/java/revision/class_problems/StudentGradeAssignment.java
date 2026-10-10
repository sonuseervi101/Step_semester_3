package revision.class_problems;

public class StudentGradeAssignment {

    static char getGrade(int marks) {
        if (marks >= 90) {
            return 'A';
        } else if (marks >= 75) {
            return 'B';
        } else if (marks >= 60) {
            return 'C';
        } else if (marks >= 40) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static void main(String[] args) {
        int[] inputs = {72, 95, 35};

        for (int marks : inputs) {
            System.out.println("Grade " + getGrade(marks));
        }
    }
}