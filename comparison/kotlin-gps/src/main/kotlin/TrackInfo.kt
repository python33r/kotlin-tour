import kotlin.system.exitProcess

const val METRES_TO_KM = 0.001

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Error: filename required on command line")
        exitProcess(1)
    }

    val result = runCatching {
        with(Track(args[0])) {
            println("Points in track: $size")

            lowestPoint?.let { println("Lowest point is $it") }
            highestPoint?.let { println("Highest point is $it") }

            val distKm = totalDistance * METRES_TO_KM
            println("Total distance travelled = %.3f km".format(distKm))
            averageSpeed?.let { println("Average speed = %.1f m/s".format(it)) }
        }
    }

    if (result.isFailure) {
        println("Computation failed with an exception!")
        println(result.exceptionOrNull())
        exitProcess(2)
    }
}
