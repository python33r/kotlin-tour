// Companion object example

class Person private constructor(val name: String) {
    // 'private constructor' above indicates that we cannot
    // create a Person object directly (see below)

    companion object {
        // Code below would be a static field and static method in Java,
        // but in Kotlin we must put it inside a 'companion object'

        private val names = mutableSetOf<String>()

        // Instead of allowing direct creation of Person objects, we
        // provide a public 'factory method' to do this:

        fun create(name: String): Person {
            // Taking control of creation means we can do things like
            // enforcing the uniqueness of a person's name
            require(name !in names) { "name already in use" }
            names.add(name)
            return Person(name)
        }
    }

    override fun toString() = "Person(name=\"$name\")"
}


// Line below would give a compiler error
//p = Person("Joe")

println("Creating Joe...")
val p1 = Person.create("Joe")
println(p1)

println("Creating Sarah...")
val p2 = Person.create("Sarah")
println(p2)

// Code below fails, because we've already created a Joe

println("Creating Joe...")
val p3 = Person.create("Joe")
println(p3)
