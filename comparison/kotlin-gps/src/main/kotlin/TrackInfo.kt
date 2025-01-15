import kotlin.system.exitProcess

fun printf(fmt: String, vararg args: Any) = print(String.format(fmt, *args))

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Error: filename required on command line")
        exitProcess(1)
    }

    try {
        with (Track(args[0])) {
            println("Points in track: $size")

            lowestPoint?.let { println("Lowest point is $it") }
            highestPoint?.let { println("Highest point is $it") }

            printf("Total distance travelled = %.3f km\n", totalDistance / 1000.0)
            averageSpeed?.let { printf("Average speed = %.1f m/s\n", it) }
        }
    }
    catch (error: Exception) {
        println("Error: ${error.message}")
        exitProcess(2)
    }
}
