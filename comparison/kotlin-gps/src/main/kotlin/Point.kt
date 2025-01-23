import java.time.ZonedDateTime
import kotlin.math.*

class Point(
    val time: ZonedDateTime,
    val longitude: Double,
    val latitude: Double,
    val elevation: Double
) {
    companion object {
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

    override fun toString() = String.format(
        "(%.5f, %.5f), %.1f m", longitude, latitude, elevation)

    fun distanceTo(p: Point): Double {
        val phi1 = latitude * PI / 180.0
        val phi2 = p.latitude * PI / 180.0

        val lambda1 = longitude * PI / 180.0
        val lambda2 = p.longitude * PI / 180.0
        val delta = abs(lambda1 - lambda2)

        val firstTerm = cos(phi2)*sin(delta)
        val secondTerm = cos(phi1)*sin(phi2) - sin(phi1)*cos(phi2)*cos(delta)
        val top = sqrt(firstTerm*firstTerm + secondTerm*secondTerm)
        val bottom = sin(phi1)*sin(phi2) + cos(phi1)*cos(phi2)*cos(delta)

        return MEAN_EARTH_RADIUS * atan2(top, bottom)
    }
}
