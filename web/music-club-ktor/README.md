# music-club-ktor

Website for a music club, implemented using [Ktor][ktr], [Pebble][peb]
templates and the [Exposed][orm] ORM framework.

Examine `Tables.kt` to see how tables are defined using Exposed. Examine
`CreateDb.kt` to see how Exposed's DSL for SQL statements is used to
populate those tables. Examine `Query.kt` to see how Exposed's DSL can be
used to perform queries.

The web application itself is defined in `Application.kt`, `Database.kt`,
`Templates.kt` and `Routing.kt`. Most of the logic is in the last of these
files.

The application uses Exposed's Data Access Objects API to perform queries.
`Entities.kt` provides the entity class definitions and shows how they are
mapped onto the corresponding database tables.

Note: Availability of JDK 21 is assumed. If you have a different version
on your system, edit `build.gradle.kts` and change the version number
supplied to `jvmToolchain` accordingly.

To work with this application in IntelliJ IDEA, simply open this directory
as a new project. IntelliJ should create Gradle tasks which will be
accessible from the Gradle tool panel, in the `application` section of the
task list. You can use the `createdb` task to create the database, and the
`query` task to run some queries against it.

The `run` task will run the server. You can also run the server via the
`EngineMain` run configuration that IntelliJ creates automatically for you.

To work from the command line, use the provided Gradle script. For example,
to create the database, do

    ./gradlew createdb

and similarly for the `query` and `run` tasks.

Note that you will need to execute the `createdb` task once only, before
doing anything else. After that, you can execute the `run` task to run
the server. When the server is up and running, visit http://localhost:7070/
to interact with the application.

[ktr]: https://ktor.io/
[peb]: https://pebbletemplates.io/
[orm]: https://jetbrains.github.io/Exposed/
