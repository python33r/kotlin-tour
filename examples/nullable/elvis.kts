// Example of using the elvis operator

// If we need to return some default value when the result
// of processing is null we can do an explicit check:

fun processText(str: String?): String {
  val result = str?.reversed()?.uppercase()
  return if (result == null) "<no data>" else result
}

// Alternatively, we can determine value to be returned in a
// more concise manner using '?:', the elvis operator:

fun processTextElvis(str: String?): String {
  return str?.reversed()?.uppercase() ?: "<no data>"
}

print("Enter a string: ")
val input = readLine()

println()
println(processText(input))
println(processTextElvis(input))
