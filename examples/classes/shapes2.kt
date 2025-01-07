// Example of inheritance from an abstract superclass

typealias Coord = Pair<Double,Double>

fun at(x: Double, y: Double) = Coord(x, y)

// abstract implies open, so we can inherit from Shape
// and override the two properties area & perimeter

abstract class Shape(val position: Coord) {
    abstract val area: Double
    abstract val perimeter: Double
}

class Circle(pos: Coord, val radius: Double): Shape(pos) {
    // Need to use 'override' when defining concrete
    // implementations of perimeter & area
    override val area get() = Math.PI * radius * radius
    override val perimeter get() = 2.0 * Math.PI * radius
}

class Rectangle(pos: Coord, val width: Double, val height: Double): Shape(pos) {
    override val area get() = width * height
    override val perimeter get() = 2.0 * (width + height)
}

// Test program

fun main() {
    //val s = Shape(at(0.0, 0.0))   // nope, Shape is now abstract!

    // Create a list of shapes

    val shapes = listOf(
        Circle(at(-2.5, 7.0), radius=12.4),
        Rectangle(at(3.0, 1.5), width=6.2, height=4.8),
        Circle(at(2.0, 4.0), radius=1.75),
    )

    // Print areas of shapes and total area

    shapes.forEach {
        // Demonstration of polymorphism: this loop doesn't need to
        // know anything about which types of shape are in list

        println(it.area)
    }

    val total = shapes.sumOf { it.area }

    println("\nTotal area = $total")
}
