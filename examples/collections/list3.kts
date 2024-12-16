// List properties and methods in Kotlin

val x = listOf(9, 3, 6, 2, 8, 5)

println("x = $x")

// Properties

println()
println("Size of x = ${x.size}")
println("Last index = ${x.lastIndex}")

// Methods

println()
println("Is x empty? ${x.isEmpty()}")
println("Does x contain 5? ${x.contains(5)}")
println("Does x contain 7? ${7 in x}")
println("First element is ${x.first()}")  // throws exception for empty lists
println("Last element is ${x.last()}")
println("Minimum is ${x.min()}")
println("Maximum is ${x.max()}")
println("Sum is ${x.sum()}")
println("Average is ${x.average()}")

val numEven = x.count { it % 2 == 0 }
println("There are ${numEven} even values")

println()
println("Reversed   : ${x.reversed()}")         // these methods return lists
println("Ascending  : ${x.sorted()}")
println("Descending : ${x.sortedDescending()}")

println()
println("x = $x")                               // ...so x remains unchanged
