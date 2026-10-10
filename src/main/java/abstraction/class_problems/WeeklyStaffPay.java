package abstraction.class_problems;

import java.util.Scanner;

public class WeeklyStaffPay {

    // Abstract: a generic "staff member" with no pay rule can never be created
    static abstract class Staff {
        protected final String name;

        Staff(String name) {   // abstract classes CAN have constructors: subclasses call them with super()
            this.name = name;
        }

        abstract double calculatePay();   // forces every staff type to define its own rule
    }

    static class FullTimeStaff extends Staff {
        private final double weeklySalary;

        FullTimeStaff(String name, double weeklySalary) {
            super(name);
            this.weeklySalary = weeklySalary;
        }

        @Override
        double calculatePay() { return weeklySalary; }
    }

    static class HourlyStaff extends Staff {
        private static final int REGULAR_HOURS = 40;
        private final double hours;
        private final double rate;

        HourlyStaff(String name, double hours, double rate) {
            super(name);
            this.hours = hours;
            this.rate = rate;
        }

        @Override
        double calculatePay() {
            if (hours <= REGULAR_HOURS) {
                return hours * rate;
            }
            double overtimeHours = hours - REGULAR_HOURS;
            return REGULAR_HOURS * rate + overtimeHours * rate * 1.5;
        }
    }

    static class InternStaff extends Staff {
        private final double stipend;

        InternStaff(String name, double stipend) {
            super(name);
            this.stipend = stipend;
        }

        @Override
        double calculatePay() { return stipend; }
    }

    // Factory: reads the right values for each staff type
    static Staff createStaff(String type, Scanner sc) {
        String name = sc.next();
        return switch (type) {
            case "FULLTIME" -> new FullTimeStaff(name, sc.nextDouble());
            case "HOURLY" -> new HourlyStaff(name, sc.nextDouble(), sc.nextDouble());
            case "INTERN" -> new InternStaff(name, sc.nextDouble());
            default -> throw new IllegalArgumentException("Unknown staff type: " + type);
        };
    }

    public static void main(String[] args) {
        String input = """
                3
                FULLTIME Asha 12000
                HOURLY Ravi 45 200
                INTERN Neha 5000
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        Staff[] staff = new Staff[n];
        for (int i = 0; i < n; i++) {
            staff[i] = createStaff(sc.next(), sc);
        }

        double total = 0;
        for (Staff s : staff) {
            double pay = s.calculatePay();
            System.out.printf("%s: %.2f%n", s.name, pay);
            total = total + pay;
        }
        System.out.printf("Total Payroll: %.2f%n", total);
    }
}