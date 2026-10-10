package oop.class_problems;

// The class that represents ONE placement record
class PlacementRecord {
    String studentName;
    String company;
    double packageLpa;

    // Constructor: sets all three fields when the object is created
    PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    // Instance method: prints this record's details
    void printRecord() {
        System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA");
    }
}

public class PlacementRecordManagement {
    public static void main(String[] args) {
        PlacementRecord[] records = {
                new PlacementRecord("Ravi", "TCS", 4.5),
                new PlacementRecord("Anitha", "Zoho", 6.2),
                new PlacementRecord("Karthik", "Infosys", 4.0)
        };

        for (PlacementRecord record : records) {
            record.printRecord();
        }
    }
}