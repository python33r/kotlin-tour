// Kotlin program to analyze the Titanic passengers dataset

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.readCSV

fun main() {
    // Load dataset, converting the 'survived' column from
    // integer (0 or 1) to boolean

    val df = DataFrame.readCSV("data/titanic.csv", delimiter=';')
        .convert { "survived"<Int>() }.toBoolean()

    println("Dataset has ${df.columnsCount()} columns, ${df.count()} rows")

    // Define some column accessors, to simplify things

    val survived by column<Boolean>()
    val sex by column<String?>()
    val age by column<Int?>()
    val pclass by column<Int>()

    // Filter dataset in various ways

    val survivors = df.filter { survived() }
    val maleSurvivors = survivors.filter { sex() == "male" }
    val childSurvivors = survivors.dropNulls { age }.filter { age()!! < 18 }

    println("There were ${survivors.count()} survivors of the Titanic")
    print("Of these, ${maleSurvivors.count()} were male")
    println(" and ${childSurvivors.count()} were below 18 years of age")

   // Create tables showing how numbers of survivors and victims
   // break down by passenger class

    val survivorsByClass = survivors.groupBy { pclass }
        .aggregate { count() into "count" }

    println("\nSurvivor counts by passenger class:")
    println(survivorsByClass)

    val victimsByClass = df.filter { !survived() }.groupBy { pclass }
        .aggregate { count() into "count" }

    println("Victim counts by passenger class:")
    println(victimsByClass)
}
