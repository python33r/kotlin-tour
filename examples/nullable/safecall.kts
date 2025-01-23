// Example of using the 'safe call' operator
// (compare with nullcheck.kts)

fun processString(str: String?): String? {
  return str?.reversed()?.uppercase()
}

print("Enter a string: ")
val input = readLine()   // input will be null if Ctrl+D pressed

val result = processString(input)
println(result)
