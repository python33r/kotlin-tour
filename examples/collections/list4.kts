// Mutable lists in Kotlin

val x = mutableListOf(9, 3, 6, 2)
val y = mutableListOf<Int>()       // type of contents needed for empty lists

println("x = $x")
println("y = $y")

println("Changing an element of x...")

x[0] = 1

println("x = $x")

println("Appending elements to y...")

y.add(8)   // appends 8 to list
y += 5     // appends 5 with operator syntax

println("y = $y")
