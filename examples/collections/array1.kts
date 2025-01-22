// Array creation, pretty printing & iteration in Kotlin

import java.util.Arrays

// Add an extension property to IntArray (see below)
val IntArray.asString get() = this.joinToString(prefix="[", postfix="]")

// Creation

val x = intArrayOf(9, 3, 6, 2, 8, 5)    // primitive ints
val y = arrayOf(9, 3, 6, 2, 8, 5)       // Integer objects - less efficient!
val z = arrayOf("One", "Two", "Three")  // Strings

val v = DoubleArray(3)                  // 3 doubles, all 0.0
val w = IntArray(5) { i -> i*i*i }      // cubes of ints in range 0..4

// Pretty printing

println(x)                    // no automatic 'pretty printing' of arrays
println(x.asString)           // ...but we can invoke our own code to do it
println(Arrays.toString(x))   // ...or we can call out to Java API

// Iteration: standard 'for each' loop

for (value in x) {
    println(value)
}

// Iterating over paired indices and values

for ((index, value) in x.withIndex()) {
    println("$index: $value")
}

// Loops above can also be written in a functional style:

x.forEach { println(it) }

x.forEachIndexed { i, value -> println("$i: $value") }
