#import "@preview/gentle-clues:1.0.0": *

#import "@preview/codly:1.1.1": *
#import "@preview/codly-languages:0.1.1": *
#show: codly-init.with()

#import "@preview/touying:0.5.3": *
#import themes.simple: *
#show: simple-theme.with(
  aspect-ratio: "16-9",
  config-common(
    preamble: {
      codly(
        display-icon: false,
        display-name: false,
        languages: codly-languages,
        number-format: (n) => text(gray, numbering("1", n)),
        stroke: 1.5pt + luma(200),
      )
    },
    //show-notes-on-second-screen: right,
  ),
)

#show raw: set text(font: "Fira Code", weight: 400)

#title-slide[
= A Tour of Kotlin
#v(1em)
Nick Efford
#v(.5em)
#image("kodee.png")
]

== Objectives

- To take you on a whistle-stop tour of Kotlin's features

- To give you a feel for what the language is like, rather than a detailed
  understanding of how to use it

Naturally, we will miss a _lot_ of stuff out...

#v(.3em)
#code(title: "Code Examples")[
  `https://github.com/python33r/kotlin-examples.git`
]

= Kotlin Basics

== Simplest Program

Similar to Python (and run a similar way):

```kotlin
println("Hello World!")
```

#pause

(Technically, this is _KotlinScript_ rather than Kotlin...)

#speaker-note[
  Demo: `kotlin hello.kts`
]

== Simplest Program, Take 2

```kotlin
fun main() {
    println("Hello World!")
}
```

- Less 'scaffolding' than Java
- No need to define classes unless we want them!
- No semicolons!

#pause

How do we compile/run this?

== Compiling & Running (JVM)

For this simple case:

#pad(left: 1em, no-codly[
  ```
  kotlinc hello.kt    # generates HelloKt.class
  kotlin HelloKt
  ```
])

More generally (and portably):

#pad(left: 1em, no-codly[
  ```
  kotlinc -include-runtime -d app.jar file1.kt file2.kt...
  kotlin app.jar
  java -jar app.jar   # also works!
  ```
])

#v(.3em)
(or let your IDE handle it... #emoji.face.wink)

#speaker-note[
  Frustratingly, you need to bundle the KSL with your code if you want to
  use the second approach, even though the `kotlin` application is perfectly
  capable of locating the KSL itself.

  On the plus side, the JAR file yielded by second approach is portable to
  systems that don't have Kotlin installed; all they need is a JVM.
]

== Program Args & String Interpolation

#codly(highlights: (
  (line: 0, start: 10, end: 28),
  (line: 2, start:24, end: 33),
))
```kotlin
fun main(args: Array<String>) {
    if (args.size > 0) {
        println("Hello ${args[0]}!")
    }
    else {
        println("Hello World!")
    }
}
```

#speaker-note[
  Function args are declared name first, followed by a colon, followed
  by the type.

  Can also see here that conditionals look very Java-like.

  String interp is similar to Python f-strings, except that the braces are
  not required when just using a variable's name, but the `$` prefix is
  always required.
]

== Variables

- Introduced using `val` or `var`

- Type can be specified explicitly:

  #pad(left: 1.5em, no-codly[```
  val name: String = "Nick"
  var total: Int = 0
  ```])

- ...or we can exploit *type inference* (preferred):

  #pad(left: 1.5em, no-codly[```
  val name = "Nick"
  var total = 0
  ```])

== `val` or `var` ?

- `val` permits assignment _once only_
  - Note: `val` #sym.equiv.not `const`

- `var` permits reassignment, allowing variable to be updated

#pause

#v(0.5em)
#tip(title: "Idiomatic Kotlin")[
Prefer `val` to `var`; use latter only when updating is necessary
]

== Built-in Data Types

Very similar to Java, except
- No primitives (but see arrays later...)

- `Int`, `Char` rather than `Integer`, `Character`

- Kotlin has unsigned versions of integer types

- Kotlin expands Java types using *extension functions*
  - You can write these yourself---e.g., to add your own functionality to
    the `String` class!

#speaker-note[
  Although you don't manipulate primitives in Kotlin, compiler can optimize
  away the use of objects when generating the Java bytecode.

  Using extension functions is a nicer solution than subclassing a built-in
  type just to add a tiny bit of functionality to it.
]

== Conditionals

- You've seen `if`...`else` already

- Conditionals are much like they are in Java---but with the important
  difference that they are *expressions* in Kotlin!

  #pad(left: 1.5em, `val largest = if (a > b) a else b`)

== `when` Expressions

```kotlin
val grade = when (mark) {
  in 70..100 -> "Distinction"
  in 40..69  -> "Pass"
  in 0..39   -> "Fail"
  else       -> throw Exception("Bad mark: $mark")
}
```

#speaker-note[
  Powerful feature, similar to Python's `match`.

  However, `when` in Kotlin can operate as a expression _or_ statement.

  (In former case, it needs to be exhaustive.)
]

== `when` as a Statement

```kotlin
when (dayOfWeek) {
  "Monday", "Tuesday"  -> println("At work")
  "Wednesday"          -> println("Day off")
  "Thursday", "Friday" -> println("At work again")
  "Saturday"           -> println("Shopping")
  "Sunday"             -> println("Sleep")
}
```

== Loops <touying:hidden>

#slide[
  'For each', like Python:

  ```kotlin
  for (i in 1..10) {
      println(i)
  }
  ```

  #pause

  ```kotlin
  for (i in 1..<10) {
      println(i)
  }
  ```

  #speaker-note[
    Second range example can also be written `1 until 10`.

    Can express a descending range with `10 downTo 1`.

    Ranges in either direction can also include a `step` parameter.
  ]
][
  #pause

  Regular `while` loop:

  ```kotlin
  var n = 1
  while (n <= 10) {
      println(n)
      n++
  }
  ```
]

== Simple Repetition

```kotlin
repeat(10) {
    println("Hello!")
}
```

- `repeat` is a _function_ that executes given code repeatedly

- First argument is number of repetitions

- Second argument, enclosed in `{}`, is a *lambda expression* containing
  the code to be executed

#speaker-note[
  Note how second arg is not within the parentheses!

  (It can be put there, but Kotlin allows lambdas that are the final
  argument to be put outside, which looks neater)
]

== Collections

#table(
  columns: 2,
  inset: 10pt,
  stroke: none,
  [`Pair`], [tuple of two values],
  [`Triple`], [tuple of three values],
  [`Array`], [fixed-sized collection of primitives or objects],
  [`List`], [ordered collection of objects],
  [`Set`], [unordered collection of unique objects],
  [`Map`], [unordered collection of keys mapped to values]
)

== Creation Patterns

Kotlin provides handy 'factory functions' to create collections:
```kotlin
val fruit = setOf("Apple", "Banana", "Kiwi")
fruit.add("Orange")
```

#pause

#error[
Line 2 in this example won't compile!

`arrayOf`, `listOf`, `setOf`, etc, return *immutable collections*
]

== Mutability

If you want mutable collections, you must be explicit:

```kotlin
val fruit = mutableSetOf("Apple", "Banana", "Kiwi")
fruit.add("Orange")
```

```kotlin
val data = mutableListOf<Double>()
File("data.txt").forEachLine {
    data.add(it.toDouble())
}
println(data.average())
```

#speaker-note[
  Note use of `val` in both examples---OK since list isn't being reassigned
  even though it is changing.

  Second example shows just one of several ways to read lines of text from
  a file. `File` is the standard class from `java.io` package and
  `forEachLine` is an extension function plugged into the class by Kotlin.
  
  Note also how easy it is to convert a string to a number, or compute the
  average of a collection of values.
]

== Element Access

- Use `[]` or `get()` method for retrieval
  - Also available: `getOrElse()` and `getOrNull()`
  - These all work with strings too!

- Use `[]` or `set()` to modify an element in mutable collections

- Arrays, lists, strings can all be *sliced*:

  ```kotlin
  val numbers = intArrayOf(9, 3, 6, 2, 8, 5)
  println(numbers.slice(2..4))
  ```

#speaker-note[
  What do you think this prints?
]

== Iteration Patterns

#no-codly[
  ```
  for (item in array) {...}
  for (item in list) {...}
  for ((index, item) in list.withIndex()) {...}
  for (element in set) {...}
  for (key in map.keys) {...}
  for (value in map.values) {...}
  for ((key, value) in map) {...}
  ```
]

#v(.5em)
Basically, what you'd expect (or hope) to see!

== Functions: Expression Form

Remember that `when` expression from earlier?

#pause

```kotlin
fun grade(mark: Int) = when (mark) {
  in 70..100 -> "Distinction"
  in 40..69  -> "Pass"
  in 0..39   -> "Fail"
  else       -> throw Exception("Bad mark: $mark")
}
```

#pause

```kotlin
println(grade(42))   // prints Pass
```

#speaker-note[
  You always need to specify types in parameter list.

  You often don't need to specify the return type with expression form, as
  you can rely on type inference.
]

== Functions: Block Form

#codly(highlights: (
  (line: 0, start: 38, end: 45),
  (line: 5, start: 5, end: 14),
))
```kotlin
fun sumSquares(numbers: List<Double>): Double {
    var sum = 0.0
    for (number in numbers) {
        sum += number * number
    }
    return sum
}
```

== But Wait...

We don't really need to write a `sumSquares` function.

Kotlin's *functional programming* support let us do it in one line!

#pad(left: 1.5em)[`numbers.sumOf { it * it }`]

#pause

#v(.5em)
- `sumOf()` is a method we can call on collections
- It expects a 'selector function' that determines values to be summed
- We provide that selector as a lambda expression
- Values from collection referenced via special variable `it`

== Other Examples

Take a list of integers and generating a new list containing the squares
of only the even values:

#pad(left: 1.5em, no-codly[
  ```
  numbers.filter { it % 2 == 0 }
         .map { it * it }
  ```
])

#v(.5em)
Remove empty strings from a list and organise remaining strings into
groups keyed by first letter:

#pad(left: 1.5em, no-codly[
  ```
  names.filter { it.isNotBlank() }
       .groupBy { it.first() }
  ```
])

#speaker-note[
  Second example returns a map of chars onto lists of strings.

  Both of these are efficient. The `filter` doesn't create an intermediate
  list.

  Many complex operations can be expressed very concisely using this
  functional programming style.
]

== Things We've Not Covered

These are important, but we don't have time to look at them.

- Nullable types
- Scope functions
  - `also`, `apply`, `let`, `run`, `with`
- Extension functions

= Classes & OOP

== Simplest Class

== Inheritance
