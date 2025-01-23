// Comparison of nullable and non-nullable types

var x: Int = 42
var y: Int? = 42

println("x = $x")
println("y = $y")

// Line below is a compiler error: x is non-nullable
//x = null

// This is OK, as y is nullable
y = null

println("y = $y")
