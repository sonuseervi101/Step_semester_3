package abstraction.assigment_problems;

import java.util.Scanner;

public class CollegeFeeCounter {

    // Interface: an ability only some students have
    interface UsesCollegeBus {
        double TRANSPORT_FEE = 12000;   // written in ONE place only

        default double getTransportFee() {
            return TRANSPORT_FEE;
        }
    }

    // Abstract class: what EVERY student has
    static abstract class Student {
        protected static final double NORMAL_TUITION = 40000;

        protected final String name;

        Student(String name) {
            this.name = name;
        }

        abstract double calculateBaseFee();   // tuition + any type-specific charges
    }

    static class DayScholar extends Student implements UsesCollegeBus {
        DayScholar(String name) { super(name); }

        @Override
        double calculateBaseFee() { return NORMAL_TUITION; }
    }

    static class Hosteller extends Student {   // lives on campus: does NOT implement UsesCollegeBus
        private static final double HOSTEL_FEE = 60000;

        Hosteller(String name) { super(name); }

        @Override
        double calculateBaseFee() { return NORMAL_TUITION + HOSTEL_FEE; }
    }

    static class ScholarshipStudent extends Student implements UsesCollegeBus {
        ScholarshipStudent(String name) { super(name); }

        @Override
        double calculateBaseFee() { return NORMAL_TUITION / 2; }   // half tuition
    }

    // Factory: the only place that looks at the type text
    static Student createStudent(String type, String name) {
        return switch (type) {
            case "DAY_SCHOLAR" -> new DayScholar(name);
            case "HOSTELLER" -> new Hosteller(name);
            case "SCHOLAR" -> new ScholarshipStudent(name);
            default -> throw new IllegalArgumentException("Unknown student type: " + type);
        };
    }

    public static void main(String[] args) {
        String input = """
                3
                DAY_SCHOLAR Asha
                HOSTELLER Ravi
                SCHOLAR Neha
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            students[i] = createStudent(type, name);
        }

        double totalCollected = 0;
        for (Student s : students) {
            double fee = s.calculateBaseFee();

            // Ask "does this student use the bus?", not "which type is this?"
            if (s instanceof UsesCollegeBus busUser) {
                fee = fee + busUser.getTransportFee();
            }

            System.out.printf("%s: %.2f%n", s.name, fee);
            totalCollected = totalCollected + fee;
        }
        System.out.printf("Total Collected: %.2f%n", totalCollected);
    }
}