package polymorphism.assigment_problems;

import java.util.Scanner;

public class FestivalBonusCalculator {

    // Base class: shared details (name, salary) + a common operation (calculateBonus)
    static abstract class Employee {
        protected final String name;
        protected final double monthlySalary;

        Employee(String name, double monthlySalary) {
            this.name = name;
            this.monthlySalary = monthlySalary;
        }

        String getName() {
            return name;   // shared behaviour: inherited by all employee types
        }

        abstract double calculateBonus();
    }

    static class FullTimeEmployee extends Employee {
        FullTimeEmployee(String name, double salary) { super(name, salary); }

        @Override
        double calculateBonus() { return monthlySalary * 0.10; }   // 10% of salary
    }

    static class PartTimeEmployee extends Employee {
        PartTimeEmployee(String name, double salary) { super(name, salary); }

        @Override
        double calculateBonus() { return monthlySalary * 0.05; }   // 5% of salary
    }

    static class Intern extends Employee {
        Intern(String name, double salary) { super(name, salary); }

        @Override
        double calculateBonus() { return 2000; }   // fixed, whatever the salary
    }

    // Factory: the only place that looks at the type text
    static Employee createEmployee(String type, String name, double salary) {
        return switch (type) {
            case "FULLTIME" -> new FullTimeEmployee(name, salary);
            case "PARTTIME" -> new PartTimeEmployee(name, salary);
            case "INTERN" -> new Intern(name, salary);
            default -> throw new IllegalArgumentException("Unknown employee type: " + type);
        };
    }

    public static void main(String[] args) {
        String input = """
                3
                FULLTIME Asha 50000
                PARTTIME Ravi 30000
                INTERN Neha 15000
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            double salary = sc.nextDouble();
            employees[i] = createEmployee(type, name, salary);
        }

        // Uniform processing: no type checks
        double total = 0;
        for (Employee e : employees) {
            double bonus = e.calculateBonus();
            System.out.printf("%s: %.2f%n", e.getName(), bonus);
            total = total + bonus;
        }
        System.out.printf("Total Bonus: %.2f%n", total);
    }
}