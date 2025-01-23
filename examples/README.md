# examples

Various examples of Kotlin code, to help you get a sense of what Kotlin
feels like as a language.

Note: the instructions below assume the use of the
[Kotlin command-line compiler](https://kotlinlang.org/docs/command-line.html),
which generates bytecode to run on the Java Virtual Machine.

## `.kts` Files

Files with names ending in `.kts` are KotlinScript files. These do not need
to be compiled explicitly. You can run them with

    kotlin file.kts

**Note**: many of the examples here are `.kts` files for convenience only,
because they contain simple demonstrations of Kotlin syntax rather than
complete programs. Kotlin isn't really designed to be a scripting language,
so for serious programming tasks you should write your code to be compiled
explicitly (see below).

## `.kt` Files

Files with names ending in `.kt` are Kotlin code rather than KotlinScript.
As with Java, you'll need to compile these explicitly:

    kotlinc file.kt

`.class` containing Java bytecode files will be generated for any classes
defined in `file.kt`. Additionally, if `file.kt` contains a `main()` function,
a file `FileKt.class` will be generated, containing this code. You can run
this with

    kotlin FileKt

You can bundle the bytecode for the main program and all classes defined
across a set of `.kt` files like this:

    kotlinc -include-runtime -d app.jar file1.kt file2.kt...

Use of the `-include-runtime` option means that `app.jar` will include any
classes used from the Kotlin standard library and will therefore be portable
to any system with a JVM. It can be run with

    kotlin app.jar

or, on systems without Kotlin installed,

    java -jar app.jar
