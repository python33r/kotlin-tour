// Use of maps in Kotlin

/*
  Creation of immutable map:

    val fixedPrices = mapOf("apple" to 25, "orange" to 47)
    fixedPrices["orange"] = 42   // compiler error!

  Creation of empty mutable map:

    val prices = mutableMapOf<String,Int>()
*/

// Creation from key-value pairs

val prices = mutableMapOf("apple" to 25, "orange" to 47)
println("Created instance of ${prices::class}...")

println("\nInitial prices: $prices")

// Changing values and adding new key value pairs

println("\nChanging price of apples")
prices["apple"] = 29
println("Adding kiwi fruit")
prices["kiwi"] = 31
println("New prices: $prices")

// Looking up a value returns a nullable Int

var p: Int? = prices["orange"]
println("\nPrice of an orange is ${p ?: "unknown"}")

p = prices["banana"]
println("Price of a banana is ${p ?: "unknown"}")

// Map properties & iteration

println("\nThere are ${prices.size} key-value pairs")

println("\nKeys are:")
for (key in prices.keys) {
    println(key)
}

println("\nValues are:")
for (value in prices.values) {
    println(value)
}

println("\nKey-value pairs are:")
for ((key, value) in prices) {
    println("$key: $value")
}

/*
  Loops above can also be written in a functional style:

    prices.keys.forEach { println(it) }

    prices.values.forEach { println(it) }

    prices.forEach { k, v -> println("$k: $v") }
*/
