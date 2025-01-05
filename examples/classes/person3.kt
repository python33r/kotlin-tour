import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Person(var name: String, val birth: LocalDate) {
    var isMarried = false

    // You can define your own methods

    fun maritalStatus() = if (isMarried) "married" else "single"

    fun atLeastAge(years: Int): Boolean {
        val today = LocalDate.now()
        val age = ChronoUnit.YEARS.between(birth, today)
        return age >= years
    }

    // Inherited methods can be overridden, but we have
    // to do so using the 'override' keyword

    override fun toString() = "$name, born on $birth, ${maritalStatus()}"
}

// Test program for class

fun main() {
    val date = LocalDate.of(1992, 8, 23)
    val p = Person("Joe", birth=date)

    println(p)   // toString() invoked automatically here

    if (p.atLeastAge(18)) {
        println("${p.name} is old enough to vote")
    }
}
