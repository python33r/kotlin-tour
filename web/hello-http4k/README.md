# hello-http4k

A very basic 'Hello World' application, implemented using [http4k][h4k],
with accompanying tests. Compare this with `hello-javalin` and `hello-ktor`.

Note: Availability of JDK 21 is assumed. If you have a different version
on your system, edit `build.gradle.kts` and change the version number
supplied to `jvmToolchain` accordingly.

To work with this in IntelliJ IDEA, simply open this directory as a new
project. IntelliJ should create Gradle tasks which will be accessible from
the Gradle tool panel.

To work with this from the command line, use the provided Gradle script.
Run the tests with

    ./gradlew test

Run the server with

    ./gradlew run

Then try visiting these URLs

    http://localhost:7070/
    http://localhost:7070/hello/joe/

[h4k]: https://http4k.org/
