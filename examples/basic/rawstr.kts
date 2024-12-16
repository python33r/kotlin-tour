// Example of raw strings and string trimming

val raw1 = """
           This is an example of a raw string
               It can contain unescaped quotes: "Hello"
           And it can also span multiple lines!
           """

val raw2 = """
           |This is an example of a raw string
               |It can contain unescaped quotes: "Hello"
           |And it can also span multiple lines!
           """

println(raw1)

println("------------")

println(raw1.trimIndent())

println("------------")

println(raw2.trimMargin())

// Note: "|" is the default prefix, but you can supply any prefix
// string you like as an argument to trimMargin()
