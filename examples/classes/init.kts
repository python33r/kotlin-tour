// Example of property validation on instantiation

class Person(val name: String) {
    init {
        // require will throw IllegalArgumentException, using text
        // generated by the provided lambda expression, if the given
        // test evaluates to false

        require(name.isNotBlank()) { "name cannot be blank or empty" }
    }

    var isMarried = false
}

fun makePerson(name: String) {
    println("\nAttempting to create a Person named '$name'...")
    val person = Person(name)
    println("Person's name is ${person.name}")
}

makePerson("Joe")
makePerson("")      // should produce an exception