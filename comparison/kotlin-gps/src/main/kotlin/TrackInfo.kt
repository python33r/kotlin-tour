import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Error: filename required")
        exitProcess(1)
    }

    try {
        with (Track(args[0])) {
            println("$size points in track")
            println("Lowest point is ${lowestPoint()}")
            println("Highest point is ${highestPoint()}")

            val distKm = totalDistance() / 1000.0
            System.out.printf("Total distance travelled = %.3f km\n", distKm)
            System.out.printf("Average speed = %.3f m/s\n", averageSpeed())
        }
    }
    catch (error: Exception) {
        println("Error: ${error.message}")
        exitProcess(2)
    }
}
