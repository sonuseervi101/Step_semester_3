package oop.assigment_problems;

class HallTicket {
    String studentName;
    int seatNumber;

    HallTicket(String studentName, int seatNumber) {
        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }
}

public class HallTicketReferenceManagement {
    public static void main(String[] args) {
        HallTicket priya = new HallTicket("Priya", 0);   // ONE real object
        HallTicket copy = priya;                         // no "new": same object, second name

        copy.seatNumber = 45;                            // change through the second variable

        System.out.println("Priya's seatNumber (via first variable): " + priya.seatNumber);
        System.out.println("copy == priya: " + (copy == priya));

        HallTicket separate = new HallTicket("Priya", 45);   // a different object
        System.out.println("separate == priya: " + (separate == priya));
    }
}
