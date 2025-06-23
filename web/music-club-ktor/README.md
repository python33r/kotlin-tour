# music-club-ktor

Website for a music club, implemented using [Ktor][ktr], [Pebble][peb]
templates and the [Exposed][orm] ORM framework.

The back end is implemented in three files. `Tables.kt` defines the database
schema. `Entities.kt` implements the object-relational mapping, providing
entity classes for the tables defined in `Tables.kt`. `Database.kt` contains
the database configuration code needed by the  web app (basically, just a
function call that connects to the database).

`Database.kt` also contains a separate program that creates the database and
populates its tables with some useful content. It uses Exposed's DSL for
SQL statements to do this.

An additional program, in `QueryDemo.kt`, demonstrates how the SQL DSL can
be used to perform queries. (The web app itself uses the ORM instead.)

`Templates.kt` contains the configuration needed to support the use of
Pebble templates.

The bulk of the application logic is in `Routing.kt`. This sets up the
routing of GET and POST requests to the code that can handle the request.
That code uses Exposed's Data Access Objects API to make the necessary
queries, then uses query results to render the appropriate template into
HTML.

`Application.kt` provides the entry point for the application. It delegates
configuration to the extension functions defined in the aforementioned files,
and specifies a server to run the application.

Note: Availability of JDK 21 is assumed. If you have a different version
on your system, edit `build.gradle.kts` and change the version number
supplied to `jvmToolchain` accordingly.

To work with this application in IntelliJ IDEA, simply open this directory
as a new project. IntelliJ should create Gradle tasks which will be
accessible from the Gradle tool panel, in the `application` section of the
task list. You can use the `createdb` task to create the database, and the
`querydemo` task to run some queries against it.

The `run` task will run the server. You can also run the server via the
`EngineMain` run configuration that IntelliJ creates automatically for you.

To work from the command line, use the provided Gradle script. For example,
to create the database, do

    ./gradlew createdb

and similarly for the `querydemo` and `run` tasks.

Note that you will need to execute the `createdb` task once only, before
doing anything else. After that, you can execute the `run` task to run
the server. When the server is up and running, visit http://localhost:7070/
to interact with the application.

[ktr]: https://ktor.io/
[peb]: https://pebbletemplates.io/
[orm]: https://jetbrains.github.io/Exposed/
