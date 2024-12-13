// Examples of string manipulation in Kotlin

val str = "Hello World!"

// Accessing properties of a string

println(str.length)
println(str.lastIndex)

// Indexing and extracting substrings

println(str[0])
println(str[7])
println(str.get(7))   // can use get() method if you prefer

println(str.subSequence(0, 7))   // character at 7 not included
println(str.slice(0..7))         // character at 7 included
println(str.slice(0..<7))        // character at 7 not included
println(str.slice(0 until 7))    // same as 0..<7

// Code below won't work

//println(str[12])      // 'index out of bounds' exception
//str[0] = 'J'          // compiler error: strings are *immutable*
