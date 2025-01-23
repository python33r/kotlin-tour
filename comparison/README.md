# comparison

Side-by-side comparison of Java and Kotlin code.

`java-gps` contains the solution to an old piece of COMP1721 coursework,
an application to analyze GPS data.

`kotlin-gps` contains a similar solution in Kotlin. This is a mostly straight
conversion, but with two significant differences.

First, the Kotlin solution implements lowest point, highest point, total
distance and average speed as **computed properties**, rather than as explicit
methods.

Second (and relatedly), the Kotlin solution allows lowest point, highest
point and average speed to be `null` when sensible values cannot be
determined, rather than following the Java solution's approach of throwing
an instance of a custom exception class. This is more in keeping with our
implementation of these quantities as properties, and it doesn't cause
any great inconvenience in the main program because Kotlin provides
elegant ways of handling null values - see `TrackInfo.kt` for examples.

## Unit Tests

It is also worth comparing the unit tests in `java-gps/src/test/java` and
`kotlin-gps/src/test/kotlin`. The former are written using JUnit 5 and
the [Hamcrest][ham] matchers. The latter are written using the [Kotest][ko]
framework. The Java unit tests are reasonably fluent, but the Kotlin tests
are easier to read (and write).

## Code Size

To estimate Source Lines Of Code (SLOC) for the two examples, run this
script:

    ./sloc.sh

You'll see from this that the Kotlin application is over 45% shorter than
the Java application, or about 38% shorter if you include the unit tests
in the calculation.

## Running The Applications

For each of these applications, you can run them from their respective
directories with

    ./gradlew run

You can run the unit tests with

    ./gradlew test

(On Windows systems, omit the `./`)

[ham]: https://hamcrest.org/JavaHamcrest/
[ko]: https://kotest.io/
