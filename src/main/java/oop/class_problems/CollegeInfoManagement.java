package oop.class_problems;

class Student {
    // Instance fields: every student has their OWN copy
    String name;
    double attendance;

    // Static fields: ONE shared copy for the whole class
    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;

    Student(String name, double attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;   // runs every time a Student is created
    }

    // Static method: uses only static fields, never name or attendance
    static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }
}

public class CollegeInfoManagement {
    public static void main(String[] args) {
        Student s1 = new Student("Ravi", 92.5);
        Student s2 = new Student("Anitha", 88.0);

        Student.printCollegeInfo();   // called through the CLASS name, not s1 or s2
    }
}