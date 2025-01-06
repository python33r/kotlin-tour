# classes

Examples of defining and using classes in Kotlin.

## Basic Examples

`Account.java` contains the definition of a basic entity class in Java,
for representing bank accounts, plus a small test program.

`account.kt` is the Kotlin equivalent of `Account.java`. Notice how close
to 50 lines of Java class definition condense to *a single line of Kotlin*.

(To be fair, Java has the `Record`, with which a simple entity class can
also be defined with a single line of code - but this works only for cases
where all fields of the class are immutable.)

`person.kt` contains a basic definition for a `Person` class, plus a test
program. This class has a read-only property and a writeable property.
It has a constructor that initializes these properties, but it does no
validation on the values used for initialization. It has implicit getter
and setter methods for the properties, but no other methods.

`person2.kt` is a slightly more complex version of `Person` demonstrating
how we can introduce properties that are not initialized via the constructor.

`person3.kt` is yet another version of `Person`, this time showing how
methods can be defined or overridden in a class.

## More Advanced Examples

`rectangle.kt` contains the definition of a `Rectangle` class, plus a test
program. This is more sophisticated than the `Person` class. It has an
`init` block to validate constructor parameters. This uses the `require`
function, which generates an `IllegalArgumentException` if the tests
performed on the supplied values for width or height are false.

The `Rectangle` class also defines custom setters that carry out equivalent
validation to the `init` block. Together, `init` and the custom setters
ensure that `Rectangle` object can never be given an invalid width or height.

`init.kts` is another example of doing validation with an `init` block,
this time using the `Person` class.

`dataclass.kts` demonstrates the effects of turning a regular class into a
data class, using `Person` as an example.

`companion.kts` demonstrates the use of a 'companion object'. This is how
Kotlin provides the equivalent of Java's static fields and static methods.
In this example, the companion object is used to partially implement the
Factory Method design pattern.

`shapes.kt` shows how to inherit from a superclass in Kotlin; `shapes2.kt`
expands on this example, making the superclass abstract; `shapes3.kt`
further expands on the example by introducing and using an interface.
