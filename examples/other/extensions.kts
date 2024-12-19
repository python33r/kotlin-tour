// Examples of extensions to built-in types

// Example 1: adding a randomize() method to strings

fun String.randomize() = this.toList().shuffled().joinToString("")

val str = "Hello World"
println("Original string: $str")
println("Randomized: ${str.randomize()}")

// Example 2: extending Int with isEven & isOdd properties

val Int.isEven: Boolean get() = this % 2 == 0
val Int.isOdd: Boolean get() = ! isEven

println("Is 1 even? ${1.isEven}")
println("Is 1 odd? ${1.isOdd}")

println("Is 2 even? ${2.isEven}")
println("Is 2 odd? ${2.isOdd}")
