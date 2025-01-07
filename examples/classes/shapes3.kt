// Defining & implementing an interface

typealias Coord = Pair<Double,Double>

interface Drawable {
    fun draw()
}

abstract class Shape(val position: Coord) {
    abstract val area: Double
    abstract val perimeter: Double
}

class Circle(pos: Coord, val radius: Double): Shape(pos), Drawable {
    override val perimeter get() = 2.0 * Math.PI * radius
    override val area get() = Math.PI * radius * radius

    override fun draw() {
        println("Drawing Circle at $position, r=$radius")
    }
}

class Rectangle(pos: Coord, val width: Double, val height: Double):
  Shape(pos), Drawable {
    override val perimeter get() = 2.0 * (width + height)
    override val area get() = width * height

    override fun draw() {
        println("Drawing Rectangle at $position, w=$width, h=$height")
    }
}

class Text(val position: Coord, val content: String): Drawable {
    override fun draw() {
        println("""Drawing "$content" at $position""")
    }
}

fun main() {
    // Create a list of Drawable objects

    val things = listOf(
        Circle(-2.5 to 7.0, radius=12.4),
        Rectangle(3.0 to 1.5, width=6.2, height=4.8),
        Circle(2.0 to 4.0, radius=1.7),
        Text(5.0 to 5.0, "Hello World!")
    )

    // Draw the objects

    things.forEach {
        it.draw()
    }
}
