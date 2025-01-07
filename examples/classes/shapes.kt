// Basic example of inheritance

typealias Coord = Pair<Double,Double>

fun at(x: Double, y: Double) = Coord(x, y)

// Shape provides a 'position' property and is open for inheritance

open class Shape(val position: Coord)

// Circle inherits position property from Shape and adds a new
// property, radius, plus computed properties for area & perimeter

class Circle(pos: Coord, val radius: Double): Shape(pos) {
    val perimeter get() = 2.0 * Math.PI * radius
    val area get() = Math.PI * radius * radius
}

// Rectangle inherits position, adds width & height properties,
// plus computed properties for area & perimeter

class Rectangle(pos: Coord, val width: Double, val height: Double): Shape(pos) {
    val perimeter get() = 2.0 * (width + height)
    val area get() = width * height
}

// Test program

fun main() {
    val s = Shape(at(0.0, 0.0))   // allowed, but not useful

    val c = Circle(at(-2.5, 7.0), radius=12.5)
    val r = Rectangle(at(3.0, 1.5), width=6.2, height=4.8)

    println("""
        Circle is at ${c.position}
        Circle radius = ${c.radius}
        Circle area = ${c.area}
        Circle perimeter = ${c.perimeter}
    """.trimIndent())

    println("""
        Rectangle is at ${r.position}
        Rectangle width = ${r.width}
        Rectangle height = ${r.height}
        Rectangle area = ${r.area}
        Rectangle perimeter = ${r.perimeter}
    """.trimIndent())
}
