// Example of delegating to a map for property storage

import java.time.LocalDate

class Person(val map: Map<String,Any?>) {
    val name: String by map
    val birth: LocalDate by map
}

val p = Person(
    mapOf(
        "name" to "Joe",
        "birth" to LocalDate.of(1992, 8, 23)
    )
)

println(p.name)
println(p.birth)
println(p.map)
