# Week 9 — Abstraction & Interfaces: Quiz and Concept Answers

## Part B — Quiz Answers

| Q | Answer | Reason |
|---|--------|--------|
| 1 | C | Abstraction hides *how* an object works and exposes only *what* it does. (B describes encapsulation.) |
| 2 | C | An abstract class can never be instantiated with `new`, even if it has no abstract methods. It does not need any abstract method, and Java classes can extend only one class. |
| 3 | B | A concrete class must implement every inherited abstract method; otherwise it must itself be declared abstract, or it will not compile. |
| 4 | C | An abstract method must be overridden, but a private method is invisible to subclasses, so it can never be overridden. |
| 5 | C | Interface fields are implicitly `public static final`, which makes them constants (for example `Insurable.INSURANCE_RATE`). |
| 6 | C | A class can implement any number of interfaces, even while extending a class. |
| 7 | D | Interface methods are implicitly public, and an implementation cannot reduce visibility, so it must be `public`. |
| 8 | C | Interfaces hold no instance state, so implementing several cannot create conflicting inherited fields (the diamond problem). |
| 9 | D | Two conflicting default methods are ambiguous, so compilation fails until the class overrides the method itself. |
| 10 | B | An abstract class's constructor runs, through `super(...)`, as part of creating a subclass object. |

## Part C — Concept Answers

**1. Abstraction in my own words**
Abstraction means using something through a simple interface without needing to know how it works inside. Example: a mobile banking app's "Pay via UPI" button. The user sees only the payee, the amount and a PIN prompt. Hidden from them are bank-to-bank routing, encryption of the PIN, fraud checks, and how the two account balances are updated. In code, `Plot.calculateArea()` works the same way: the report calls it without knowing whether the shape uses π, multiplication or halving.

**2. Abstraction vs encapsulation**
Abstraction is about design: deciding *what* operations an object exposes and hiding *how* they work (abstract methods, interfaces). Encapsulation is about protection: keeping an object's data private and allowing changes only through controlled methods. They work together in one class: for example `Booking` exposes `calculateTotal()` (abstraction: callers do not see how the fee is added) while keeping `BOOKING_FEE` private (encapsulation: nobody can change or skip it).

**3. Constructors in abstract classes**
Even though an abstract class cannot be created on its own, it often holds fields that every subclass shares, and those fields still need to be set up. Its constructor does that once instead of repeating it in every subclass. It runs whenever a subclass object is created: the subclass constructor calls `super(...)` as its first step, which runs the abstract class's constructor before the rest of the subclass constructor. Example: `Staff(String name)` in WeeklyStaffPay sets the name for every staff type.

**4. Why an abstract method cannot be private, static, or final**
- **private:** private methods are invisible to subclasses, so no subclass could ever provide the required implementation.
- **static:** static methods belong to the class, not to objects, and are not overridden polymorphically; an abstract method needs a per-subclass implementation chosen at runtime.
- **final:** final means "cannot be overridden", while abstract means "must be overridden"; the two contradict each other.

**5. The diamond problem**
The diamond problem happens when a class inherits from two parents that both inherit from (or define) the same thing: if both parents have a field or method with the same name, it is unclear which one the child gets. Java avoids it for state by allowing only one parent class, so a class can never inherit two conflicting sets of fields. A class can implement many interfaces, but interfaces have no instance fields, and if two interfaces provide conflicting default methods, the compiler forces the class to override the method and resolve the conflict explicitly (as in Quiz Q9).