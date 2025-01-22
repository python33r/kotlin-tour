// Array indexing and slicing in Kotlin

// Extension property to facilitate 'pretty printing'
val IntArray.asString get() = this.joinToString(prefix="[", postfix="]")

val x = intArrayOf(9, 3, 6, 2, 8, 5)

println(x::class)
println(x.asString)

println()
println("x[0] = ${x[0]}")
println("x.get(3) = ${x.get(3)}")
println("x.slice(2..4) is ${x.slice(2..4)}")
println("x.slice(2 until 4) is ${x.slice(2 until 4)}")
