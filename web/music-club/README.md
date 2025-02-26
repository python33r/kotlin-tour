# music-club

Website for a music club, implemented using [Javalin][jav], [Pebble][peb]
templates and the [Exposed][orm] ORM framework.

Examine `Tables.kt` to see how tables are defined using Exposed. Examine
`CreateDb.kt` to see how Exposed's DSL for SQL statements is used to
populate those tables. Examine `Query.kt` to see how Exposed's DSL is used
to perform queries.

The web application itself is defined in `MusicClub.kt`. It performs its
queries in a different way, using Exposed's Data Access Objects API.
`Entities.kt` provides the entity class definitions and shows how they are
mapped onto the corresponding database tables.

Note: Availability of JDK 21 is assumed. If you have a different version
on your system, edit `build.gradle.kts` and change the version number
supplied to `jvmToolchain` accordingly.

To work with this application in IntelliJ IDEA, simply open this directory
as a new project. IntelliJ should create Gradle tasks which will be
accessible from the Gradle tool panel. You'll need to use the `createdb`,
`query` and `run` tasks from the `application` section of the task list.

To work from the command line, use the provided Gradle script. For example,
to create the database, do

    ./gradlew createdb

and similarly for the `query` and `run` tasks.

Note that you will need to execute the `createdb` task once only, before
doing anything else. After that, you can execute the `run` task to run
the server. When the server is up and running, visit http://localhost:7070/
to interact with the application.

[jav]: https://javalin.io/
[peb]: https://pebbletemplates.io/
[orm]: https://jetbrains.github.io/Exposed/
