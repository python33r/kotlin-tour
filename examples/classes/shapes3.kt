// Example of defining & implementing an interface

typealias Coord = Pair<Double,Double>

fun at(x: Double, y: Double) = Coord(x, y)

// Drawable interface specifies the behaviour that all drawable
// things have in common: they must all have a draw() method

interface Drawable {
    fun draw()
}

// Class hierarchy for shapes

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

// Text class implements Drawable but is otherwise
// unrelated to classes inheriting from Shape

class Text(val position: Coord, val content: String): Drawable {
    override fun draw() {
        println("""Drawing "$content" at $position""")
    }
}

// Test program

fun main() {
    // Create a list of Drawable objects

    val things = listOf(
        Circle(at(-2.5, 7.0), radius=12.4),
        Rectangle(at(3.0, 1.5), width=6.2, height=4.8),
        Circle(at(2.0, 4.0), radius=1.7),
        Text(at(5.0, 5.0), "Hello World!")
    )

    // Draw the objects

    things.forEach {
        it.draw()
    }
}
