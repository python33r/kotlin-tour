// Basic example of inheritance

typealias Coord = Pair<Double,Double>

open class Shape(val position: Coord)

class Circle(pos: Coord, val radius: Double): Shape(pos) {
    // Circle inherits 'position' property from Shape
    // and adds two computed properties of its own

    val perimeter get() = 2.0 * Math.PI * radius
    val area get() = Math.PI * radius * radius
}

class Rectangle(pos: Coord, val width: Double, val height: Double): Shape(pos) {
    // Rectangle inherits 'position' property from Shape
    // and adds two computed properties of its own

    val perimeter get() = 2.0 * (width + height)
    val area get() = width * height
}

fun main() {
    val s = Shape(0.0 to 0.0)   // allowed, but not useful

    val c = Circle(-2.5 to 7.0, radius=12.5)
    val r = Rectangle(3.0 to 1.5, width=6.2, height=4.8)

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
