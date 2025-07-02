import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.withClue
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import java.time.ZonedDateTime
import kotlin.math.PI

@Suppress("unused")
class PointTest : StringSpec({
    val time = ZonedDateTime.now()
    val point = Point(time, 1.5, 50.0, 25.0)
    val tolerance = 1e-6
    val zero = 0.0 plusOrMinus tolerance
    val halfCircumference = (PI * Point.MEAN_EARTH_RADIUS) plusOrMinus tolerance

    "Properties have expected values" {
        withClue("Time") { point.time shouldBe time }
        withClue("Longitude") { point.longitude shouldBe (1.5 plusOrMinus tolerance) }
        withClue("Latitude") { point.latitude shouldBe (50.0 plusOrMinus tolerance) }
        withClue("Elevation") { point.elevation shouldBe (25.0 plusOrMinus tolerance) }
    }

    "Exception if longitude < -180 degrees" {
        shouldThrow<IllegalArgumentException> {
            Point(time, -180.5, 0.0, 0.0)
        }
    }

    "Exception if longitude > 180 degrees" {
        shouldThrow<IllegalArgumentException> {
            Point(time, 180.5, 0.0, 0.0)
        }
    }

    "Exception if latitude < -90 degrees" {
        shouldThrow<IllegalArgumentException> {
            Point(time, 0.0, -90.5, 0.0)
        }
    }

    "Exception if latitude > 90 degrees" {
        shouldThrow<IllegalArgumentException> {
            Point(time, 0.0, 90.5, 0.0)
        }
    }

    "Correct string representation" {
        point.toString() shouldBe "(1.50000, 50.00000), 25.0 m"
    }

    "Correct distance between poles" {
        val northPole = Point(time, 0.0, 90.0, 0.0)
        val southPole = Point(time, 0.0, -90.0, 0.0)
        withClue("North to itself") {
            northPole.distanceTo(northPole) shouldBe zero
        }
        withClue("North to South") {
            northPole.distanceTo(southPole) shouldBe halfCircumference
        }
    }

    "Correct distance hetween points on equator" {
        val p1 = Point(time, 0.0, 0.0, 0.0)
        val p2 = Point(time, 180.0, 0.0, 0.0)
        val p3 = Point(time, -180.0, 0.0, 0.0)

        withClue("To itself") { p1.distanceTo(p1) shouldBe zero }
        withClue("Opposing point (+180°)") { p1.distanceTo(p2) shouldBe halfCircumference }
        withClue("Opposing point (-180°)") { p1.distanceTo(p3) shouldBe halfCircumference }
        withClue("+180° to -180° (same point)") { p2.distanceTo(p3) shouldBe zero }
    }
})
