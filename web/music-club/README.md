# music-club

Website for a music club, implemented using [Javalin][jav], [Pebble][peb]
templates and the [Exposed][orm] ORM framework.

To work with this in IntelliJ IDEA, simply open this directory as a new
project. IntelliJ should create Gradle tasks which will be accessible from
the Gradle tool panel. You'll need to use the `createdb`, `query` and `run`
tasks from the `application` section of the task list.

To work with this from the command line, use the provided Gradle script.

Create the database with

    ./gradlew createdb

Examine `Tables.kt` to see how tables are defined using Exposed. Examine
`CreateDb.kt` to see how Exposed's DSL for SQL statements is used to
populate those tables.

You can then do

    ./gradlew query

This runs the program in `Query.kt`, which shows how Exposed's DSL is used
to perform queries.

The web application itself is defined in `MusicClub.kt`. It performs its
queries in a different way, using Exposed's Data Access Objects API.
`Entities.kt` provides the entity class definitions and shows how they are
mapped onto the corresponding database tables.

Run the server with

    ./gradlew run

Then visit http://localhost:7070/

[jav]: https://javalin.io/
[peb]: https://pebbletemplates.io/
[orm]: https://jetbrains.github.io/Exposed/
