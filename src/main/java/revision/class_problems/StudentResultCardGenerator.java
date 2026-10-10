package revision.class_problems;

class Student {
    private final String name;
    private final int[] marks;

    Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    double calculateAverage() {
        int sum = 0;
        for (int mark : marks) {
            sum = sum + mark;
        }
        return (double) sum / marks.length;   // cast avoids integer division
    }

    char getGrade() {
        double average = calculateAverage();
        if (average >= 75) {
            return 'B';
        } else if (average >= 60) {
            return 'C';
        } else if (average >= 40) {
            return 'D';
        } else {
            return 'F';
        }
    }

    void printResultCard() {
        System.out.printf("%s: Average %.1f, Grade %c%n",
                name.toUpperCase(), calculateAverage(), getGrade());
    }
}

public class StudentResultCardGenerator {
    public static void main(String[] args) {
        Student[] students = {
                new Student("Asha", new int[]{80, 90, 70}),
                new Student("Ravi", new int[]{60, 55, 50})
        };

        for (Student s : students) {
            s.printResultCard();
        }
    }
}