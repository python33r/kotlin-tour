# comparison

Side-by-side comparison of Java and Kotlin code.

`java-gps` contains the solution to an old piece of COMP1721 coursework,
an application to analysis GPS data.

`kotlin-gps` contains the equivalent solution in Kotlin. Note that this a
more-or-less straight conversion, in which we haven't made optimal use
of language features. Nevertheless, it is significantly shorter in length
than the Java version.

It is also worth comparing the unit tests in `java-gps/src/test/java` and
`kotlin-gps/src/test/kotlin`. The former are written using JUnit 5 and
the Hamcrest matchers. The latter are written using the [Kotest][ko]
framework. The Kotlin tests are particularly easy to read (and write).

For each of these applications, you can run them from their respective
directories with

    ./gradlew run

You can run the unit tests with

    ./gradlew test

[ko]: https://kotest.io/
