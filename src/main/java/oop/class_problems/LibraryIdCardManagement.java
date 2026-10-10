package oop.class_problems;

class IdCard {
    String name;
    int booksIssued;

    IdCard(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
    }
}

public class LibraryIdCardManagement {
    public static void main(String[] args) {
        IdCard ravi = new IdCard("Ravi", 0);   // creates ONE real object
        IdCard duplicate = ravi;               // no "new": just a second name for the SAME object

        duplicate.booksIssued = 3;             // change it through the second variable

        System.out.println("Ravi's booksIssued (via first variable): " + ravi.booksIssued);
        System.out.println("duplicate == ravi: " + (duplicate == ravi));

        IdCard separate = new IdCard("Ravi", 3);   // a brand new, different object
        System.out.println("separate == ravi: " + (separate == ravi));
    }
}