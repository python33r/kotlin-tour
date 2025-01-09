// Example of using Kotlin's vetoable delegate

import kotlin.properties.Delegates.vetoable

// count will be initialized to 0; subsequent changes that would
// increase its value beyond 9 will be silently prevented

var count by vetoable(0) { _, _, new -> new < 10 }

repeat(15) {
    println(count)
    ++count
}
