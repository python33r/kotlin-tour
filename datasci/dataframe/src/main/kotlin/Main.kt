import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.readCSV

fun main() {
    val df = DataFrame.readCSV("data/titanic.csv", delimiter=';')
        .convert { "survived"<Int>() }.toBoolean()

    println("Dataset has ${df.columnsCount()} columns, ${df.count()} rows")

    val survived by column<Boolean>()
    val sex by column<String?>()
    val age by column<Int?>()
    val pclass by column<Int>()

    val survivors = df.filter { survived() }
    val maleSurvivors = survivors.filter { sex() == "male" }
    val childSurvivors = survivors.dropNulls { age }.filter { age()!! < 18 }

    println("There were ${survivors.count()} survivors of the Titanic")
    print("Of these, ${maleSurvivors.count()} were male")
    println(" and ${childSurvivors.count()} were below 18 years of age")

    val survivorsByClass = survivors.groupBy { pclass }
        .aggregate { count() into "count" }

    println("\nSurvivor counts by passenger class:")
    println(survivorsByClass)

    val victimsByClass = df.filter { !survived() }.groupBy { pclass }
        .aggregate { count() into "count" }

    println("Victim counts by passenger class:")
    println(victimsByClass)
}
