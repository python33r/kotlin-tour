// Inheritance from an abstract superclass

typealias Coord = Pair<Double,Double>

// abstract implies open here - so Shape can be overridden,
// as can the two computed properties area & perimeter

abstract class Shape(val position: Coord) {
    abstract val area: Double
    abstract val perimeter: Double
}

class Circle(pos: Coord, val radius: Double): Shape(pos) {
    // Need to use 'override' when defining concrete
    // implementations of perimeter & area
    override val perimeter = 2.0 * Math.PI * radius
    override val area = Math.PI * radius * radius
}

class Rectangle(pos: Coord, val width: Double, val height: Double): Shape(pos) {
    override val perimeter = 2.0 * (width + height)
    override val area = width * height
}

fun main() {
    //val s = Shape(0.0 to 0.0)   // nope, Shape is now abstract!

    // Create a list of shapes

    val shapes = listOf(
        Circle(-2.5 to 7.0, radius=12.4),
        Rectangle(3.0 to 1.5, width=6.2, height=4.8),
        Circle(2.0 to 4.0, radius=1.75),
    )

    // Draw the shapes

    shapes.forEach {
        // Demonstration of polymorphism: this loop doesn't need to
        // know anything about which types of shape are in list

        println("Shape area = ${it.area}")
    }
}
