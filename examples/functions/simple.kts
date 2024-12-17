// Examples of simple functions with expression bodies

fun distance(x: Double, y: Double) = Math.sqrt(x*x + y*y)

println("Distance of (1.5,2.7) from origin is ${distance(1.5, 2.7)}")

fun anagrams(p: String, q: String) =
    p.lowercase().toList().sorted() == q.lowercase().toList().sorted()

print("Are 'Peach' & 'Cheap' anagrams of each other? ")
println(anagrams("Peach", "Cheap"))

fun grade(mark: Int) = when (mark) {
    in 0..39 -> "Fail"
    in 40..69 -> "Pass"
    in 70..100 -> "Distinction"
    else -> throw IllegalArgumentException("Bad mark: $mark")
}

println("A mark of 42 is a ${grade(42)}")
