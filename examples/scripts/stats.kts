// Computes some stats for numbers in a file

import java.io.File
import kotlin.system.exitProcess

if (args.size != 1) {
    println("Usage: kotlin stats.kts <data-file>")
    exitProcess(1)
}

val data = buildList {
    File(args[0]).forEachLine {
        add(it.toDouble())
    }
}

println("Minimum = ${data.min()}")
println("Maximum = ${data.max()}")
println("Mean = ${data.average()}")
