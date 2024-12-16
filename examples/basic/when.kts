// Examples of when expressions

// Expression form, no subject (else branch required)

val a = 10
val b = 5
println("a = $a, b = $b")

val largest = when {
    a > b -> a
    else -> b
}

println("Largest value is $largest")

// Expression form with subject (else required)

print("Enter a mark: ")
val mark = readln().toInt()

val grade = when (mark) {
    in 0..39   -> "Fail"
    in 40..69  -> "Pass"
    in 70..100 -> "Distinction"
    else       -> throw Exception("Bad mark: $mark")
}

println("$mark is a $grade")

// Statement form (else not required)

print("What day is it? ")
val day = readln().lowercase()

when (day) {
    "monday", "tuesday"  -> println("At work")
    "wednesday"          -> println("Day off")
    "thursday", "friday" -> println("Still at work")
    "saturday", "sunday" -> println("Weekend!")
}
