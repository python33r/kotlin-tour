// Example of a function definition with a block body

import java.io.File
import kotlin.system.exitProcess

fun longestWord(filename: String): String {
    val spaces = "\\s+".toRegex()
    var longestInFile = ""
    File(filename).forEachLine { line ->
        val longest = line.split(spaces).maxBy { it.length }
        if (longest.length > longestInFile.length) {
            longestInFile = longest
        }
    }
    return longestInFile
}

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Error: filename required on command line")
        exitProcess(1);
    }

    val longest = longestWord(args[0])

    println("Longest word is '$longest'")
}
