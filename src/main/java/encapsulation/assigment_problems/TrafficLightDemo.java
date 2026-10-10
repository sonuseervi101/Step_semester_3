package encapsulation.assigment_problems;

class TrafficLight {
    private static final String[] CYCLE = {"RED", "GREEN", "YELLOW"};   // the fixed order

    private final String id;        // fixed at creation
    private int currentIndex;       // position in CYCLE, changed ONLY by next()

    public TrafficLight(String id) {
        this.id = id;
        this.currentIndex = 0;      // a new light starts on RED
    }

    // The only way to change the color: move one step forward, wrapping back to RED
    public String next() {
        currentIndex = (currentIndex + 1) % CYCLE.length;
        return CYCLE[currentIndex];
    }

    // Read-only
    public String getColor() {
        return CYCLE[currentIndex];
    }

    public String getId() {
        return id;
    }
}

public class TrafficLightDemo {
    public static void main(String[] args) {
        TrafficLight t = new TrafficLight("TL-9");
        System.out.println("Light " + t.getId() + " starts on: " + t.getColor());

        for (int i = 0; i < 5; i++) {
            System.out.println("next() -> " + t.next());
        }
    }
}