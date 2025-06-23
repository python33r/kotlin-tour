# music-club

Website for a music club, implemented using [Javalin][jav], [Pebble][peb]
templates and the [Exposed][orm] ORM framework.

The back end is implemented using Exposed across three files. `Tables.kt`
defines the database schema as two Kotlin objects, one for each of the tables
in  the database. `Entities.kt` implements object-relational mapping,
providing entity classes that correspond to the tables. `Databases.kt`
provides a function to connect to the database.

`Database.kt` also implements a separate program than can be used to create
and populate the database. This uses Exposed's DSL for SQL statements.

`QueryDemo.kt` is not part of the web app but demonstrates how Exposed's
SQL DSL can be used to perform database queries.

The application logic is defined in `MusicClub.kt`. This creates an  object
`MusicClub` to represent the Javalin app. When initialized, this object
connects to the database and sets up Pebble as the templating library used to
render HTML responses. It also sets up routing of GET and POST requests to
request handling code. This code makes the appropriate DB queries using
Exposed's Data Access Objects API, then uses query results to render
the response.

Note: Availability of JDK 21 is assumed. If you have a different version
on your system, edit `build.gradle.kts` and change the version number
supplied to `jvmToolchain` accordingly.

To work with this application in IntelliJ IDEA, simply open this directory
as a new project. IntelliJ should create Gradle tasks which will be
accessible from the Gradle tool panel. You'll need to use the `createdb`,
`querydemo` and `run` tasks from the `application` section of the task list.

To work from the command line, use the provided Gradle script. For example,
to create the database, do

    ./gradlew createdb

and similarly for the `querydemo` and `run` tasks.

Note that you will need to execute the `createdb` task once only, before
doing anything else. After that, you can execute the `run` task to run
the server. When the server is up and running, visit http://localhost:7070/
to interact with the application.

[jav]: https://javalin.io/
[peb]: https://pebbletemplates.io/
[orm]: https://jetbrains.github.io/Exposed/
