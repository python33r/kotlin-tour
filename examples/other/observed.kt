// Example of using Kotlin's observable delegate

import kotlin.properties.Delegates.observable

fun main() {
    // name variable will be initialized to "?";
    // subsequent changes will be reported on

    var name: String by observable("?") {
        _, old, new -> if (new != old) println("$old -> $new")
    }

    println("Initial name: ${name}")

    name = "Joe"
    name = "David"
    name = "Nick"

    println("Final name: ${name}")
}
