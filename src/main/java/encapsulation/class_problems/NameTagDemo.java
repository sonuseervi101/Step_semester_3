package encapsulation.class_problems;

// final class: no one can create a subclass that changes its behaviour
final class NameTag {
    private final String firstName;   // final: set once, never changed
    private final String lastName;

    public NameTag(String fullName) {
        String[] parts = fullName.split(" ");   // split once, here in the constructor
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    // Builds the nickname from the stored parts, e.g. "Maria G."
    public String getNickname() {
        return firstName + " " + lastName.charAt(0) + ".";
    }
}

public class NameTagDemo {
    public static void main(String[] args) {
        NameTag tag = new NameTag("Maria Gomez");
        System.out.println("Nickname: " + tag.getNickname());

        NameTag tag2 = new NameTag("Maria Gomez");
        System.out.println("tag2 nickname: " + tag2.getNickname());
        System.out.println("Same nickname: " + tag.getNickname().equals(tag2.getNickname()));
        System.out.println("Same object (tag == tag2): " + (tag == tag2));
    }
}