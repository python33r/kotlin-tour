// Program to compute the area of a triangle, showing
// more examples of functions with block bodies

fun readNumber(prompt: String): Double {
    print(prompt)
    return readln().toDouble()
}

fun area(a: Double, b: Double, c: Double): Double {
    // Compute area using Heron's formula
    val s = 0.5 * (a + b + c)
    return Math.sqrt(s * (s - a) * (s - b) * (s - c))
}

fun main() {
    val a = readNumber("Enter length of side a: ")
    val b = readNumber("Enter length of side b: ")
    val c = readNumber("Enter length of side c: ")

    println("Triangle area = ${area(a, b, c)}")
}
