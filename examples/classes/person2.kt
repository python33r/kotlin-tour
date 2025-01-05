import java.time.LocalDate

class Person(var name: String, val birth: LocalDate) {
    // If a property isn't initialised via the constructor,
    // we define it inside the class like this:
    var isMarried = false
}

// Test program for class

fun main() {
    val date = LocalDate.of(1992, 8, 23)
    val p = Person("Joe", birth=date)

    println("Name: ${p.name}")

    // Define a function to display marital status
    // (yes, we can define functions inside other functions!)

    fun maritalStatus(p: Person) = when (p.isMarried) {
        true -> "Status: married"
        false -> "Status: unmarried"
    }

    println(maritalStatus(p))

    // We can change isMarried property because it is a var

    println("Time passes...")

    p.isMarried = true
    println(maritalStatus(p))
}
