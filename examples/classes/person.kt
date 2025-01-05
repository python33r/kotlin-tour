import java.time.LocalDate   // yes, we can use Java API!

// A class with two properties: one writeable, the other read-only
// (Line below specifies properties, getters & setters and constructor!)

class Person(var name: String, val birth: LocalDate)

// Test program for class

fun main() {
    val date = LocalDate.of(1992, 8, 23)

    // Create a Person (no need for 'new', keyword args allowed)

    val p = Person("Joe", birth=date)

    println(p.name)    // getter method called here
    println(p.birth)

    // A person can change their name

    p.name = "David"   // setter method called here

    println("Name changed to ${p.name}")

    // ...but it's a compiler error if we try to make them younger!

    //p.birth = LocalDate.of(1995, 8, 23)

    // Person has two properties, a constructor, getter & setter code
    // and *nothing else* -- so, as in Java, attempting to print a Person
    // invokes default toString() method inherited by all classes

    println(p)
}
