// Examples of repetition in Kotlin

// for loops

for (n in 1..10) {
    print(" $n")
}
println()

for (n in 1..<10) {
    print(" $n")
}
println()

for (n in 1 until 10) {   // same as previous example
    print(" $n")
}
println()

for (n in 1..10 step 2) {
    print(" $n")
}
println()

for (n in 10 downTo 1) {
    print(" $n")
}
println()

for (n in 10 downTo 1 step 3) {
    print(" $n")
}
println()

// while loop

var n = 1
while (n <= 10) {
    print(" $n")
    n++
}
println()

// repeat function (neater for simple repetitions)

repeat(3) {
    println("Hello!")
}

repeat(5) { index -> println("Hello $index") }
