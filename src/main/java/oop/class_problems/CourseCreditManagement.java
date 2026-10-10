package oop.class_problems;

class Course {
    String code;
    String title;
    int credits;
    int labCredits;

    // Constructor 1: sets all four fields directly
    public Course(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }

    // Constructor 2: theory-only course, chains to Constructor 1 with labCredits = 0
    public Course(String code, String title, int credits) {
        this(code, title, credits, 0);
    }

    int totalCredits() {
        return credits + labCredits;
    }
}

public class CourseCreditManagement {
    public static void main(String[] args) {
        Course theory = new Course("21CSC201J", "Data Structures", 4);   // uses Constructor 2
        Course lab = new Course("21CSC205L", "DSA Lab", 3, 1);           // uses Constructor 1

        System.out.println(theory.code + " total credits: " + theory.totalCredits());
        System.out.println(lab.code + " total credits: " + lab.totalCredits());
    }
}