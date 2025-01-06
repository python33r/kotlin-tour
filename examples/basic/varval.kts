// var versus val

// Variables can be declared with var

var x: Int           // types can be declared explicitly
var y = 12.87        // ...or can be inferred (recommended approach)

x = 1                // initialization can be later if it is before first use
x = 42               // ...and reassignment is possible

println("x = $x, y = $y")

// It is better to use val if you won't be reassigning

val pi = 3.14159     // again, type will be inferred here

println("pi = $pi")

//pi = 3.0           // assigning a new value isn't possible
