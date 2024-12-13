// KotlinScript program containing examples of:
// functions, if & when expressions, for loops, maps

fun grade(mark: Int): String = when (mark) {
    in 70..100 -> "Distinction"
    in 40..69  -> "Pass"
    in 0..39   -> "Fail"
    else       -> throw IllegalArgumentException("invalid mark: $mark")
}

fun displayGrades(marks: Map<String,Int>) {
    if (marks.isEmpty()) {
        println("No marks available")
    }
    else {
        for ((name, mark) in marks) {
            println("$name: ${grade(mark)}")
        }
    }
}

val marks = mapOf(
    "Sarah Jones" to 72,
    "Ben Smith" to 65,
    "David Armitage" to 49,
    "Amanda Benson" to 81,
    "John Dobson" to 34,
    //"James Davis" to -1
    // uncomment line above to see the exception
)

displayGrades(marks)
