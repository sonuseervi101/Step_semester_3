# Week 8 — Inheritance & Polymorphism: Quiz and Concept Answers

## Quiz Answers

| Q | Answer | Reason |
|---|--------|--------|
| 1 | C | Polymorphism removes type-checking if-else blocks: each document overrides render(), and the caller just calls it. |
| 2 | A, B, C | A car, a rectangle and a database connection each genuinely *are* a kind of their parent. D is a "has-a" relationship (composition), not "is-a". |
| 3 | C | Runtime method dispatch picks the overriding method based on the actual object type, not the reference type. |
| 4 | C | Calling an overridden method through base-type references is inheritance-based polymorphism. |
| 5 | A, C, D | Book adds to the inherited rule (extending); LibraryItem defines the common behaviour; a DVD can be handled through a LibraryItem reference. B is wrong: DVD replaces the rule with its own fixed period, so it is overridden, not shared. |
| 6 | A, C | The actual object (Dog) decides which method runs, and Dog's makeSound() is an override. B is wrong: Animal's version is the general one. D is wrong: subclasses can add brand-new methods. |
| 7 | C | A new WalletPayment subclass works with the existing loop over Payment references, with no change to the processor. |
| 8 | A | Inheriting just to reuse code creates false "is-a" relationships and tight coupling, so changes to the parent break unrelated children. |
| 9 | A, C | The sender works with any Notification, and a new channel is just a new subclass. B and D are wrong: each channel keeps its own send() logic. |
| 10 | A, B, C | One loop handles many related types uniformly with no casting. D is wrong: new subtypes are easier to add, not harder. |

## Concept Answers

**1. Reuse and extension through inheritance**
A subclass automatically gets the fields and methods of its base class, so common code is written once. It can then add new methods or override existing ones to change behaviour. Example: in the Library Due Date program, `LibraryItem` holds the title and the shared `getDueDate()` logic; `Book`, `Dvd` and `Magazine` reuse that method unchanged and only supply their own loan period.

**2. The "is-a" relationship**
"is-a" means every object of the subclass is truly a kind of the base class and can be used anywhere the base class is expected (a `Car` is a `Vehicle`). Identifying it correctly matters because inheritance promises that the subclass supports everything the parent does. If the relationship is false, the subclass inherits methods that make no sense for it, and the design becomes confusing and fragile. If the real relationship is "has-a", composition is the right tool.

**3. Method overriding**
Overriding means a subclass provides its own version of a method already declared in the base class, with the same name and parameters. Example: `Employee` declares `calculateBonus()`. `FullTimeEmployee` returns 10% of salary, `PartTimeEmployee` returns 5%, and `Intern` returns a fixed 2000. Payroll calls `calculateBonus()` on any employee and gets the correct rule automatically (see FestivalBonusCalculator).

**4. Runtime polymorphism (dynamic dispatch)**
When a method is called through a base-type reference, Java does not decide which version to run at compile time. At runtime it checks the actual class of the object the reference points to and runs that class's override (or the inherited version if there is no override). So `Payment p = new CardPayment(...)` followed by `p.calculateFinalAmount()` runs CardPayment's version.

**5. Polymorphic collections**
A polymorphic collection is an array or list declared with the base type that holds objects of different subclasses, for example `Payment[] payments` holding Card, Wallet and BankTransfer objects. One loop can process every element by calling the shared method, and each object applies its own rule. Advantages: shorter code, no type checks or casts, and new subtypes fit into the same collection without changing the loop.

**6. Polymorphism vs type-based conditionals**
With conditionals, every place that needs type-specific behaviour has its own if-else or switch on the type; adding a new type means finding and editing all of them, and missing one causes bugs. With polymorphism, each type's behaviour lives in its own class, and callers just call the method. Polymorphism keeps related logic together, follows the open/closed principle (open for extension, closed for modification), and is easier to test and maintain.

**7. Adding new types with minimal changes**
Because processing code only depends on the base type, a new subclass plugs straight in. Example: to add `UpiPayment` to the payment system, create `class UpiPayment extends Payment` with its own `calculateFinalAmount()`, and add one line to the factory that creates objects from input. The loop that totals all payments does not change at all.

**8. Inherited vs overridden behaviour**
Inherited behaviour is a base-class method the subclass uses as-is, because the logic is the same for all types (for example `getDueDate()` and `getName()`). Overridden behaviour is a method the subclass replaces because its rule is different (for example `getLoanDays()` and `calculateBonus()`). A subclass inherits when the general logic fits and overrides when it needs its own specific version.

**9. Inheritance only for code reuse**
If two classes are unrelated but one extends the other just to borrow some methods, the subclass also inherits everything else in the parent, including methods that make no sense for it. It becomes tightly coupled to the parent, so changes there can break it unexpectedly, and the type system lets it be used where the parent is expected, which is logically wrong. Composition (holding a helper object) or a shared utility is the better choice for plain reuse.

**10. VehicleRental: common vs specialized behaviour**
Common to all rentals: customer name, rental days, a base daily rate, and steps like generating an invoice; these belong in a base `VehicleRental` class. Specialized: `CarRental` might add a fuel or insurance charge, while `TruckRental` might add a per-kilometre load charge and a commercial-licence check. The base class declares `calculateRentalCost()`, each subclass overrides it with its own pricing, and the rental system processes a list of `VehicleRental` objects uniformly.