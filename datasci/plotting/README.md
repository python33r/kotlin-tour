# plotting

A Kotlin application to plot weather station data using the [Kandy][kan]
data visualization library.

You can open this directory in IntelliJ and run `Main.kt` directly or
choose the `run` task from the Gradle tool window.

Alternatively, to run the application from the command line, do

    ./gradlew run

The dataset can be found in `data/leeds_2019.csv`. It consists of
measurements made at a weather station in Leeds during 2019.

The application generates two plots, exporting one as an SVG file and
the other as a PNG image. Both are stored in the `lets-plot-images`
subdirectory.

[kan]: https://kotlin.github.io/kandy/
