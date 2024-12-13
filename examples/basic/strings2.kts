// More examples of string manipulation in Kotlin

val str = "Hello World!"

// Iteration over a string: for loop

for (char in str) {
    println(char)
}

// Iteration over a string: functional style

str.forEach {
    println(it)
}

// Invoking methods on a string

println(str.isEmpty())
println(str.uppercase())   // returns a new string
println(str)               // ...so str is unchanged

val capitals = Regex("[A-Z]")
println(str.replace(capitals, replacement="_"))
