package oop.assigment_problems;

public class EmployeeProfileCreation {

    // Nested class (lives inside EmployeeProfileCreation, so it won't clash with M5's Employee)
    static class Employee {
        String empId;
        String empName;
        double salary;
        boolean isIntern;

        // Constructor 1: permanent employee
        public Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
            this.isIntern = false;
        }

        // Constructor 2: intern - chains to Constructor 1 with salary 0, then marks as intern
        public Employee(String empId, String empName) {
            this(empId, empName, 0);
            this.isIntern = true;
        }

        void printProfile() {
            System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern);
        }
    }

    public static void main(String[] args) {
        Employee permanent = new Employee("E-101", "Divya", 65000);   // Constructor 1
        Employee intern = new Employee("E-102", "Arjun");             // Constructor 2

        permanent.printProfile();
        intern.printProfile();
    }
}