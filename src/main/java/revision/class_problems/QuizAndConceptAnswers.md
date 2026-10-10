# Week 10 — Revision: Quiz and Concept Answers

## Part B — Quiz Answers

| Q | Answer | Reason |
|---|--------|--------|
| 1 | D | `new` creates two separate objects, so `==` (same object?) is false, but `equals()` (same text?) is true. |
| 2 | B | The builder holds "Java Lab"; `reverse()` flips every character, giving "baL avaJ". |
| 3 | B | One String argument matches `Student(String name)` directly; that constructor then chains to the two-argument one with `this(...)`. |
| 4 | D | `w2 = w1` copies the reference, not the object; both point to one wallet, so both see 700. |
| 5 | C | The static `count` is shared by all objects and incremented in each of the 3 constructor calls. |
| 6 | A | The loop visits indexes 0 to length − 1 in order, printing every element once. |
| 7 | B | `continue` skips the print only when i is 3. |
| 8 | A | Java passes a copy of the primitive; changing `n` inside the method does not change `x`. |
| 9 | C | `count` is a double, so the division is floating-point: 250 / 3.0 = 83.33333333333333. |
| 10 | B | The reference is a Payment, but the object is a Card, so Card's `pay()` runs: 500 + 2% = 510.0. |
| 11 | B | Strings are immutable; `toUpperCase()` returns a new String and `name` stays "java". |
| 12 | B | `s1` and `s2` share one pooled "hello"; `new String("hello")` makes one more object on the heap. |
| 13 | A | "Campus" has 6 characters; index 2 is 'm' (C=0, a=1, m=2). |
| 14 | A | Indexes start at 0: u=0, s=1, e=2, r=3, so '@' is at index 4. |
| 15 | B | The default constructor sets title to "Unknown" and price to 0.0. |
| 16 | A | The parameterized constructor copies the passed values into the fields. |
| 17 | B | Only two `new` calls; `w2` is just another reference to the first object. |
| 18 | C | Same object through both references, so both print 700. |
| 19 | C | `s1` and `s2` are different objects; changing `s1`'s name does not affect `s2`. |
| 20 | C | The static counter is shared and incremented once per object: 3. |

## Part C — Concept Answers

**1. String immutability**
A String object can never change after it is created; methods like `toUpperCase()` or `+` return a brand-new String. When a String is modified repeatedly (for example adding characters in a loop), every step creates a new object and copies all previous characters, so the cost grows roughly with the square of the length and creates lots of garbage. The fix is `StringBuilder`, which is mutable: `append()` changes the same object, and `toString()` produces the final String once at the end (as in the Word Reverser).

**2. The `this` keyword in constructors**
When a parameter has the same name as a field, the plain name refers to the parameter (it "shadows" the field). `this.name` explicitly means "this object's field", so `this.name = name;` copies the parameter into the field. Without `this`, `name = name;` would just assign the parameter to itself and the field would stay null. Example: `Student(String name, int[] marks) { this.name = name; this.marks = marks; }` in the Result Card Generator.

**3. Object vs reference variable**
An object is the actual data created in memory by `new`. A reference variable does not hold the object; it holds the object's address. Assigning one reference to another (`Wallet w2 = w1;`) copies the address, not the object, so both variables point to the same single object. Any change made through one (`w2.add(200)`) is visible through the other. To get an independent copy you must create a new object (as in the Playlist's defensive copy).

**4. Static vs instance variables**
Instance variables belong to each object, so each object has its own value; static variables belong to the class and are shared by every object. Use static for data that is truly common or class-wide, such as a college name or a counter of objects created (`studentCount`). Use instance variables for data that differs per object, such as a student's name or marks. Making per-object data static would cause every object to overwrite the same value.

**5. for vs while vs do-while**
- **for:** best when the number of iterations is known in advance, such as visiting every index of an array.
- **while:** best when the loop should continue until a condition changes and might run zero times, such as extracting digits `while (n > 0)` in the Digit Sum program.
- **do-while:** checks the condition after the body, so it always runs at least once; useful for menus or input prompts that must be shown before deciding whether to repeat.

**6. Pass-by-value**
Java always passes a copy of the argument's value. For a primitive, the method gets a copy of the number, so changing it inside the method does not affect the caller (Quiz Q8). For an object, the value copied is the reference, so the method can use it to change the object's contents, and the caller sees those changes (for example `reverseInPlace` modifying the caller's array). But reassigning the parameter to a new object inside the method does not change what the caller's variable points to.

**7. Integer division**
When both operands are integers, Java performs integer division and drops the fractional part (truncation): `7 / 2` is `3`, not `3.5`. To get a precise result, at least one operand must be a floating-point type, which can be done with a cast: `(double) sum / count`. The cast must apply before the division; `(double) (sum / count)` is still wrong because the division already happened as integers.

**8. Encapsulation**
Encapsulation means bundling data with the methods that work on it and hiding the data from direct outside access. In Java, fields are made `private`, and access is given only through methods. Getters provide read access, and setters or action methods allow changes only after validation (for example `deduct()` refusing to make a balance negative). This protects data integrity because invalid values can never be assigned directly, and the internal representation can change later without breaking other code.

**9. `==` vs `equals()`**
For objects, `==` checks whether two references point to the exact same object in memory. `equals()` is meant to check whether two objects are logically equal in content. The default `equals()` inherited from `Object` behaves like `==`; classes such as `String` override it to compare their contents. If you override `equals()` in your own class (for example comparing `name` and `booksIssued` in IdCard), two separate objects with the same field values will be considered equal (you should also override `hashCode()` to match).

**10. Overloading vs overriding**
- **Overloading:** several methods in the same class share a name but have different parameter lists. The compiler chooses which one to call based on the arguments, so it is resolved at compile time. Example: `isEligible(double cgpa)` and `isEligible(double cgpa, int codingScore)`.
- **Overriding:** a subclass provides its own version of a method with the same signature as in its parent. The JVM chooses which version runs based on the actual object, so it is resolved at runtime. Example: `CardPayment.calculateFinalAmount()` overriding the abstract method in `Payment`.