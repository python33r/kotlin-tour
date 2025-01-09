// Examples of using the 'with' scope function

// Example 1: building a string

val builder = StringBuilder()

val result = with(builder) {
    // Method calls made implicitly on builder
    append('<')
    repeat(20) { append('=') }
    append('>')
    toString()
}

println(result)

/*
   Note: above example can be written more concisely as

     val result = buildString {
         append('<')
         repeat(20) { append('=') }
         append('>')
     }
*/

// Example 2: formatted printing

with(System.out) {
    printf("\u03c0 is %.5f\n", Math.PI)
    printf("e is %.4f\n", Math.E)
}
