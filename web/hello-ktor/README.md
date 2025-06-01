# hello-ktor

A very basic 'Hello World' application, implemented using [Ktor][ktr],
with accompanying tests. Compare this with `hello-javalin` and `hello-http4k`.

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

See the table below for other relevant tasks.

| Task                          | Description                                                          |
|-------------------------------|----------------------------------------------------------------------|
| `buildFatJar`                 | Build an executable JAR of the server with all dependencies included |
| `buildImage`                  | Build the docker image to use with the fat JAR                       |
| `publishImageToLocalRegistry` | Publish the docker image locally                                     |
| `runDocker`                   | Run using the local docker image                                     |

[ktr]: https://ktor.io/
