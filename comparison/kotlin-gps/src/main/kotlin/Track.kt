import java.io.File
import java.io.IOException
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

const val NUM_CSV_COLUMNS = 4

class Track() {
    private val points = mutableListOf<Point>()

    constructor(filename: String) : this() {
        readData(filename)
    }

    fun readData(filename: String) {
        File(filename).useLines {
            points.clear()
            it.drop(1).forEach { line ->
                val parts = line.split(",")
                if (parts.size != NUM_CSV_COLUMNS) {
                    throw IOException("Invalid file format")
                }

                val time = ZonedDateTime.parse(parts[0])
                val lon = parts[1].toDouble()
                val lat = parts[2].toDouble()
                val elev = parts[3].toDouble()
                points.add(Point(time, lon, lat, elev))
            }
        }
    }

    val size get() = points.size

    operator fun get(index: Int) = points[index]

    fun add(p: Point) = points.add(p)

    fun lowestPoint() = points.minByOrNull { it.elevation }

    fun highestPoint() = points.maxByOrNull { it.elevation }

    fun totalDistance(): Double {
        var distance = 0.0
        if (points.size > 1) {
            for (i in 1..points.lastIndex) {
                distance += points[i].distanceTo(points[i - 1])
            }
        }
        return distance
    }

    fun averageSpeed(): Double? {
        if (points.size > 1) {
            val time = ChronoUnit.SECONDS.between(points.first().time, points.last().time)
            return totalDistance() / time
        }
        return null
    }
}
