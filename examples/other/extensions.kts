// Examples of extension functions and extension properties

fun String.randomize() = this.toList().shuffled().joinToString("")

println("Hello World".randomize())

val Int.isEven: Boolean get() = this % 2 == 0
val Int.isOdd: Boolean get() = ! isEven

println("Is 1 even? ${1.isEven}")
println("Is 1 odd? ${1.isOdd}")

println("Is 2 even? ${2.isEven}")
println("Is 2 odd? ${2.isOdd}")
