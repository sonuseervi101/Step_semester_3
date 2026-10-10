package oop.assigment_problems;

public class CompanyInfoManagement {

    // Nested class (separate from M3's Employee, so no name clash)
    static class Employee {
        // Instance fields: each employee has their own
        String empName;
        double salary;

        // Static fields: one shared copy for all employees
        static String companyName = "Bright Horizon Technologies";
        static int employeeCount = 0;

        Employee(String empName, double salary) {
            this.empName = empName;
            this.salary = salary;
            employeeCount++;   // runs every time an Employee is created
        }

        // Static method: uses only static fields
        static void printCompanyInfo() {
            System.out.println(companyName);
            System.out.println("Employees on record: " + employeeCount);
        }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Divya", 65000);
        Employee e2 = new Employee("Arjun", 30000);
        Employee e3 = new Employee("Meera", 72000);

        Employee.printCompanyInfo();   // called through the CLASS name, not any object
    }
}