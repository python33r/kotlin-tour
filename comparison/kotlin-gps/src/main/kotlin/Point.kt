import java.time.ZonedDateTime
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class Point(
    val time: ZonedDateTime,
    val longitude: Double,
    val latitude: Double,
    val elevation: Double,
) {
    companion object {
        const val DEGREES_TO_RADIANS = PI / 180.0
        const val MIN_LONGITUDE = -180.0
        const val MAX_LONGITUDE = 180.0
        const val MIN_LATITUDE = -90.0
        const val MAX_LATITUDE = 90.0
        const val MEAN_EARTH_RADIUS = 6.371009e+6
    }

    init {
        require(longitude in MIN_LONGITUDE..MAX_LONGITUDE) { "invalid longitude" }
        require(latitude in MIN_LATITUDE..MAX_LATITUDE) { "invalid latitude" }
    }

    override fun toString() = "(%.5f, %.5f), %.1f m".format(longitude, latitude, elevation)

    fun distanceTo(p: Point): Double {
        val phi1 = latitude * DEGREES_TO_RADIANS
        val phi2 = p.latitude * DEGREES_TO_RADIANS

        val lambda1 = longitude * DEGREES_TO_RADIANS
        val lambda2 = p.longitude * DEGREES_TO_RADIANS
        val delta = abs(lambda1 - lambda2)

        val firstTerm = cos(phi2) * sin(delta)
        val secondTerm = cos(phi1) * sin(phi2) - sin(phi1) * cos(phi2) * cos(delta)
        val top = sqrt(firstTerm * firstTerm + secondTerm * secondTerm)
        val bottom = sin(phi1) * sin(phi2) + cos(phi1) * cos(phi2) * cos(delta)

        return MEAN_EARTH_RADIUS * atan2(top, bottom)
    }
}
