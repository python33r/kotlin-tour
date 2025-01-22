// List indexing and slicing in Kotlin

val x = listOf(9, 3, 6, 2, 8, 5)

println(x::class)   // shows that Kotlin is using java.util.ArrayList
println(x)

println()
println("x[0] = ${x[0]}")
println("x.get(3) = ${x.get(3)}")
println("x.slice(2..4) is ${x.slice(2..4)}")
println("x.slice(2 until 4) is ${x.slice(2 until 4)}")

//x[0] = 1   // compiler error! (listOf() returns an immutable list)
