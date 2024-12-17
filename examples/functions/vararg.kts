// Example of using vararg

fun longestOf(vararg words: String): String {
  // words is an array of String containing all the arguments
  var longest = ""
  words.forEach {
    if (it.length > longest.length) {
      longest = it
    }
  }
  return longest
}

/*
  Note: above could be implemented in one line!

    longestOf(vararg words: String) = words.maxBy { it.length }
*/

println(longestOf("apple", "orange", "raspberry", "kiwi"))
