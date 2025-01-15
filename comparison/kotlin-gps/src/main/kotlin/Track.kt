import java.io.File
import java.io.IOException
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

const val NUM_CSV_COLUMNS = 4
const val TIME_FIELD = 0
const val LON_FIELD = 1
const val LAT_FIELD = 2
const val ELEV_FIELD = 3

class Track() {
    private val points = mutableListOf<Point>()

    constructor(filename: String) : this() {
        readData(filename)
    }

    fun readData(filename: String) {
        File(filename).useLines {
            points.clear()
            it.drop(1).forEach { line ->
                with (line.split(",")) {
                    if (size != NUM_CSV_COLUMNS) {
                        throw IOException("Invalid file format")
                    }
                    points.add(Point(
                        time = ZonedDateTime.parse(get(TIME_FIELD)),
                        longitude = get(LON_FIELD).toDouble(),
                        latitude = get(LAT_FIELD).toDouble(),
                        elevation = get(ELEV_FIELD).toDouble()
                    ))
                }
            }
        }
    }

    val size get() = points.size

    operator fun get(index: Int) = points[index]

    fun add(p: Point) = points.add(p)

    val lowestPoint get() = points.minByOrNull { it.elevation }

    val highestPoint get() = points.maxByOrNull { it.elevation }

    val totalDistance: Double
        get() {
            var distance = 0.0
            if (points.size > 1) {
                for (i in 1..points.lastIndex) {
                    distance += points[i].distanceTo(points[i - 1])
                }
            }
            return distance
        }

    val averageSpeed: Double?
        get() {
            if (points.size > 1) {
                val time = ChronoUnit.SECONDS.between(points.first().time, points.last().time)
                return totalDistance / time
            }
            return null
        }
}
