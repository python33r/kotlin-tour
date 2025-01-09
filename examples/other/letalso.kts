// Example of handling a nullable result via safe call and 'let'

print("Enter some text: ")

readLine()?.let {
    // This code won't execute if readLine() returns null
    println("Length of input = ${it.length}")
}

// Same as previous example, this time using 'also' so that the result
// of the call is returned, making it avalable outside the lambda

print("\nEnter some more text: ")

val text = readLine()?.also {
    println("Length of input = ${it.length}")
}

println("You entered $text")
