// Another example of a class

class Rectangle(w: Double, h: Double) {
    // Note: no use of val or var in parameter list, because we are
    // defining properties separately (see below)

    // Initializer block checks values supplied to constructor

    init {
        require(w > 0.0) { "invalid width: $w" }
        require(h > 0.0) { "invalid height: $h" }
    }

    // Properties width & height have custom setters to check the
    // value on the RHS of an assignment operation

    var width = w
        set(value) {
            require(value > 0) { "invalid width: $value" }
            field = value
        }

    var height = h
        set(value) {
            require(value > 0) { "invalid height: $value" }
            field = value
        }

    // isSquare, area & perimeter are dynamically computed properties
    // (read-only, no backing fields)

    val isSquare get() = width == height
    val area get() = width * height
    val perimeter get() = 2.0 * (width + height)
}


fun main() {
    val r = Rectangle(3.5, 1.2)

    println("Dimensions are ${r.width} by ${r.height}")
    println("Area is ${r.area}")
    println("Perimeter is ${r.perimeter}")

    println("Increasing height to 1.8...")
    r.height = 1.8

    println("Area is now ${r.area}")
    println("Perimeter is now ${r.perimeter}")

    println("Is it square? ${r.isSquare}")
    println("Making height equal to width...")
    r.height = r.width
    println("Is it square now? ${r.isSquare}")

    println("Attempting to make width negative...")
    r.width = -1.0
}
