// A class with two properties: one read-only, the other writeable
// (Line below specifies properties, getters & setters and constructor!)

class Person(val name: String, var isMarried: Boolean)


// Test program for class

fun main() {
    val p = Person("Joe", isMarried=true)

    println("Name: ${p.name}")

    fun maritalStatus(p: Person) = when (p.isMarried) {
        true -> "Status: married"
        false -> "Status: unmarried"
    }

    println(maritalStatus(p))

    p.isMarried = false   // a person can get divorced...

    println(maritalStatus(p))

    //p.name = "Steve"   // ...but can't change their name (compiler error)

    // Person has two properties, a constructor, getter & setter code
    // and *nothing else* - so, as in Java, attempting to print a Person
    // invokes default toString() method inherited by all classes

    println(p)
}
