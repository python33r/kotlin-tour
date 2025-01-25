# javalin-hello

A very basic 'Hello World' application, implemented using [Javalin][jav],
with accompanying tests.

The application supports GET requests along two different routes, allowing
it to issue both generic and personalized greetings. This functionality
requires very few lines of code - about 15 (including blank lines) for the
application itself, and double that for the tests.

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

[jav]: https://javalin.io/
