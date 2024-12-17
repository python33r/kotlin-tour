// Practical examples of using lambda expressions

val numbers = listOf(1, 4, 7, 2, 9, 3, 8)
println(numbers)

val even = numbers.filter { it % 2 == 0 }

println("\nThe even numbers are $even")

val greater = numbers.count { it > 7 }

println("$greater numbers are greater than 7")

val squares = numbers.map { it*it }

println("Squares are $squares")

val largeCubes = numbers.filter { it > 5 }.map { it*it*it }

println("Cubes of numbers > 5 are $largeCubes\n")

val names = listOf("Sarah", "Mike", "David", "Lucy", "Bartholomew", "Alice")
println(names)

val longest = names.maxBy { it.length }

println("\nLongest name is $longest")

val grouped = names.groupBy { it.length }

for (length in grouped.keys.sorted()) {
    println("$length letters: ${grouped[length]}")
}
