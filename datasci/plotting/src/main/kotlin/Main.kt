// Example of plotting datasets using Kandy

import kotlinx.datetime.LocalDateTime
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.util.color.Color

fun main() {
    // Read CSV, parsing measurement times

    val df = DataFrame.readCSV(
        "data/leeds_2019.csv",
        parserOptions = ParserOptions(dateTimePattern = "dd/MM/yyyy HH:mm"),
    )

    println(df.describe())

    // Create two data subsets, one consisting of measurements made at noon,
    // the other of measurements made every other hour in April

    val Time by column<LocalDateTime>()

    val noonData = df.filter { Time().hour == 12 }

    val aprilData = df.filter {
        Time().month == java.time.Month.APRIL && Time().hour % 2 == 0
    }

    // Generate scatter plot of relative humidity vs temperature at noon

    val firstPlot = noonData.plot {
        points {
            x("Temp 2m")
            y("Rel Hum %")
            color("Glob Rad W/m2")
        }
        layout {
            title = "Humidity & Temperature in Leeds During 2019"
        }
    }

    firstPlot.save("humidity-vs-temp.svg")

    // Generate time-series plot of wind speed in April

    val secondPlot = aprilData.plot {
        line {
            x("Time")
            y("W Spd m/s")
            color = Color.RED
        }
        layout {
            title = "Wind speed in Leeds, April 2019"
            size = 950 to 400
        }
    }

    secondPlot.save("wind-speed.png")
}
