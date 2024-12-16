// List creation, pretty printing & iteration in Kotlin

val x = listOf(9, 3, 6, 2, 8, 5)        // list of integers
val y = listOf("One", "Two", "Three")   // list of strings

println(x)   // automatic pretty printing works! (unlike arrays)

for (value in x) {
    println(value)
}

for ((i, value) in x.withIndex()) {
    println("$i: $value")
}

// Loops above can also be written in a functional style:

x.forEach { println(it) }

x.forEachIndexed { i, value -> println("$i: $value") }
