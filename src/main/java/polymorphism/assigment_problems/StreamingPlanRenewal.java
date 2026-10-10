package polymorphism.assigment_problems;

import java.time.LocalDate;
import java.util.Scanner;

public class StreamingPlanRenewal {

    // Base class: shared subscriber details + shared renewal logic
    static abstract class Plan {
        protected final String subscriberName;
        protected final LocalDate startDate;

        Plan(String subscriberName, LocalDate startDate) {
            this.subscriberName = subscriberName;
            this.startDate = startDate;
        }

        abstract int getValidityDays();   // each plan gives its own number

        // Shared (inherited) behaviour: works for every plan type
        LocalDate getRenewalDate() {
            return startDate.plusDays(getValidityDays());
        }
    }

    static class BasicPlan extends Plan {
        BasicPlan(String name, LocalDate start) { super(name, start); }

        @Override
        int getValidityDays() { return 30; }
    }

    static class StandardPlan extends Plan {
        StandardPlan(String name, LocalDate start) { super(name, start); }

        @Override
        int getValidityDays() { return 90; }
    }

    static class PremiumPlan extends Plan {
        PremiumPlan(String name, LocalDate start) { super(name, start); }

        @Override
        int getValidityDays() { return 365; }
    }

    // Factory: the only place that looks at the type text
    static Plan createPlan(String type, String name, LocalDate start) {
        return switch (type) {
            case "BASIC" -> new BasicPlan(name, start);
            case "STANDARD" -> new StandardPlan(name, start);
            case "PREMIUM" -> new PremiumPlan(name, start);
            default -> throw new IllegalArgumentException("Unknown plan type: " + type);
        };
    }

    public static void main(String[] args) {
        String input = """
                4
                BASIC Asha 2024-01-15
                STANDARD Ravi 2024-02-01
                PREMIUM Neha 2024-03-10
                BASIC Kiran 2024-12-20
                """;
        Scanner sc = new Scanner(input);

        int n = sc.nextInt();
        Plan[] subscribers = new Plan[n];
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            LocalDate start = LocalDate.parse(sc.next());   // reads "2024-01-15" into a date
            subscribers[i] = createPlan(type, name, start);
        }

        // Uniform processing: no type checks
        for (Plan p : subscribers) {
            System.out.println(p.subscriberName + ": " + p.getRenewalDate());
        }
    }
}