// Example of a class with an overridden equals() method
// (demonstrating type checking & smart casting)

import java.time.LocalDate

class Person(val name: String, val birth: LocalDate) {
    init {
        require(name.isNotBlank()) { "invalid name" }
    }
  
    override fun toString() = "Person(name=$name,birth=$birth)"

    override fun equals(other: Any?) =
        other is Person && name == other.name && birth == other.birth
                       // ↑
                       // | from this point on, because the type check has
                       // | succeeded, 'other' can be treated as a Person
}

fun main() {
    val date = LocalDate.of(1992, 8, 23)
    val p = Person("Joe", birth=date)
    val q = Person("Joe", birth=date)

    println(p)
    println(q)
    println("Equal? ${p == q}")
    println("Same object? ${p === q}")
}
