import kotlin.system.exitProcess

fun printf(fmt: String, vararg args: Any) = print(String.format(fmt, *args))

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Error: filename required on command line")
        exitProcess(1)
    }

    try {
        with (Track(args[0])) {
            println("$size points in track")
            println("Lowest point is ${lowestPoint()}")
            println("Highest point is ${highestPoint()}")

            val distKm = totalDistance() / 1000.0
            printf("Total distance travelled = %.3f km\n", distKm)
            printf("Average speed = %.3f m/s\n", averageSpeed())
        }
    }
    catch (error: Exception) {
        println("Error: ${error.message}")
        exitProcess(2)
    }
}
