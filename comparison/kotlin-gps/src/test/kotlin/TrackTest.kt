import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.withClue
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import java.io.IOException
import java.time.ZonedDateTime

@Suppress("unused")
class TrackTest : StringSpec({
    isolationMode = IsolationMode.InstancePerTest

    val tolerance = 1e-5
    val zero = 0.0 plusOrMinus tolerance

    val t1 = ZonedDateTime.parse("2022-02-17T09:52:39Z")
    val t2 = ZonedDateTime.parse("2022-02-17T09:53:31Z")
    val t3 = ZonedDateTime.parse("2022-02-17T09:54:29Z")
    val t4 = ZonedDateTime.parse("2022-02-17T09:55:31Z")

    val p1 = Point(t1, -1.547720, 53.803941, 69.8)
    val p2 = Point(t2, -1.548531, 53.804616, 72.5)
    val p3 = Point(t3, -1.549418, 53.805238, 68.1)
    val p4 = Point(t4, -1.550828, 53.805469, 70.5)

    val trackNoPoints = Track()

    val trackOnePoint = Track().apply {
        add(p1)
    }

    val trackFourPoints = Track().apply {
        add(p1)
        add(p2)
        add(p3)
        add(p4)
    }

    "Track starts out with a size of zero" {
        trackNoPoints.size shouldBe 0
    }

    "Points can be added to a Track" {
        trackFourPoints.size shouldBe 4
    }

    "Points can be accessed in a Track" {
        withClue("First point") { trackFourPoints[0].time shouldBe t1 }
        withClue("Last point") { trackFourPoints[3].time shouldBe t4 }
    }

    "Exception if accessing points in an empty Track" {
        shouldThrow<IndexOutOfBoundsException> {
            trackNoPoints[0]
        }
    }

    "Lowest point found correctly" {
        trackFourPoints.lowestPoint?.elevation shouldBe (68.1 plusOrMinus tolerance)
    }

    "No lowest point for an empty Track" {
        trackNoPoints.lowestPoint shouldBe null
    }

    "Highest point found correctly" {
        trackFourPoints.highestPoint?.elevation shouldBe (72.5 plusOrMinus tolerance)
    }

    "No highest point for an empty Track" {
        trackNoPoints.highestPoint shouldBe null
    }

    "Total distance calculated correctly" {
        trackFourPoints.totalDistance shouldBe (278.53495 plusOrMinus tolerance)
    }

    "Distance is 0 for an empty Track" {
        trackNoPoints.totalDistance shouldBe zero
    }

    "Distance is 0 for a single-point Track" {
        trackOnePoint.totalDistance shouldBe zero
    }

    "Average speed calculated correctly" {
        trackFourPoints.averageSpeed shouldBe (1.61939 plusOrMinus tolerance)
    }

    "No average speed for an empty Track" {
        trackNoPoints.averageSpeed shouldBe null
    }

    "No average speed for a one-point Track" {
        trackOnePoint.averageSpeed shouldBe null
    }

    "Track created from a file correctly" {
        val track = Track("data/test.csv")
        withClue("Track size") { track.size shouldBe 4 }
        withClue("Final point") { track[3].time shouldBe t4 }
    }

    "Exception for a file that doesn't exist" {
        shouldThrow<IOException> {
            Track("file-that-does-not-exist.csv")
        }
    }

    "Exception for a badly-formatted file" {
        shouldThrow<IOException> {
            Track("data/bad.csv")
        }
    }
})
