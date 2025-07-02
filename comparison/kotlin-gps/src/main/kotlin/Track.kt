import java.io.File
import java.io.IOException
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit.SECONDS

class Track() {
    companion object {
        const val NUM_CSV_COLUMNS = 4
        const val TIME_FIELD = 0
        const val LON_FIELD = 1
        const val LAT_FIELD = 2
        const val ELEV_FIELD = 3
    }

    private val points = mutableListOf<Point>()

    constructor(filename: String) : this() {
        readData(filename)
    }

    fun readData(filename: String) {
        File(filename).useLines {
            points.clear()
            it.drop(1).forEach { line ->
                with(line.split(",")) {
                    if (size != NUM_CSV_COLUMNS) {
                        throw IOException("invalid file format")
                    }
                    points.add(
                        Point(
                            time = ZonedDateTime.parse(get(TIME_FIELD)),
                            longitude = get(LON_FIELD).toDouble(),
                            latitude = get(LAT_FIELD).toDouble(),
                            elevation = get(ELEV_FIELD).toDouble(),
                        ),
                    )
                }
            }
        }
    }

    val size get() = points.size

    operator fun get(index: Int) = points[index]

    fun add(p: Point) = points.add(p)

    val lowestPoint get() = points.minByOrNull { it.elevation }

    val highestPoint get() = points.maxByOrNull { it.elevation }

    val totalDistance get() = points
        .asSequence()
        .windowed(2)
        .map { it[0].distanceTo(it[1]) }
        .sum()

    val averageSpeed get() = if (points.size > 1) {
        totalDistance / SECONDS.between(points.first().time, points.last().time)
    }
    else {
        null
    }
}
