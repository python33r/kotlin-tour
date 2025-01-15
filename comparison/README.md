# comparison

Side-by-side comparison of Java and Kotlin code.

`java-gps` contains the solution to an old piece of COMP1721 coursework,
an application to analysis GPS data.

`kotlin-gps` contains a very similar solution in Kotlin. This is a straight
conversion, more or less, but with one significant difference. Whereas
the Java solution uses a custom exception class to signal errors in three
of the methods, the Kotlin solution dispenses with this class, instead
returning null from those methods. It's reasonable to do this because
Kotlin provides very convenient ways of handling nulls - see `TrackInfo.kt`
for examples.

It is also worth comparing the unit tests in `java-gps/src/test/java` and
`kotlin-gps/src/test/kotlin`. The former are written using JUnit 5 and
the Hamcrest matchers. The latter are written using the [Kotest][ko]
framework. The Java unit tests are reasonably fluent, but the Kotlin tests
are easier to read (and write).

## Code Size

To estimate Source Lines Of Code (SLOC) for the two examples, run this
script:

    ./sloc.sh

You'll see from this that the Kotlin application is over 40% shorter than
the Java application, or about 36% shorter if you include the unit tests
in the calculation.

## Running The Applications

For each of these applications, you can run them from their respective
directories with

    ./gradlew run

You can run the unit tests with

    ./gradlew test

[ko]: https://kotest.io/
