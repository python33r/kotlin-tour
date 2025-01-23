// Explicit null checking - not the best approach!
// (compare with safecall.kts)

fun processString(str: String?): String? {
  if (str != null) {
    return str.reversed().uppercase()
  }
  return null
}

print("Enter a string: ")
val input = readLine()   // input will be null if Ctrl+D pressed

val result = processString(input)
println(result)
