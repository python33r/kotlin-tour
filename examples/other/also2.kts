// Another example of using also() - in this case, as an alternative
// to checking property initialization with initializer blocks

import java.time.LocalDate


class Person(_name: String, val birth: LocalDate) {
    var name = _name.also {
        require(_name.isNotBlank()) { "name cannot be blank" }
    }
    set (value) {
        require(value.isNotBlank()) { "cannot change name to blank string" }
        field = value
    }

    override fun toString() = "$name (born $birth)"
}


val date = LocalDate.of(1992, 8, 23)

// This triggers an exception:
//val p = Person("", date)

val p = Person("Nick", LocalDate.of(1992, 8, 23))
println(p)

// This triggers an exception:
p.name = "  "
println(p)
