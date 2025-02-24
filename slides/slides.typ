#import "@preview/gentle-clues:1.2.0": *
//#import "@preview/octique:0.1.0": octique
#import "@local/octique:0.1.0": octique    // temporary fix

#import "@preview/codly:1.2.0": *
#import "@preview/codly-languages:0.1.7": *
#show: codly-init.with()

#import "@preview/touying:0.5.5": *
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
    new-section-slide-fn: section => {
      touying-slide-wrapper(self => {
        touying-slide(self: self, {
          set align(center + horizon)
          set text(size: 2em)
          utils.display-current-heading(level: 1)
        })
      })
    },
    horizontal-line-to-pagebreak: false,
    //show-notes-on-second-screen: right,
    //handout: true,
  ),
)

// Classic hyperlink styling ;-)
#show link: it => underline(
  stroke: 1pt,
  offset: 1.5pt,
  text(blue, it)
)

#show raw: set text(font: "Fira Code", weight: 400)

#let nc(left: 1em, txt) = pad(left: left, no-codly(txt))

//=========================================================================//

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
#code(title: "Slides & Code", icon: octique("mark-github"))[
  #set text(font: "Fira Code", size: 0.8em)
  #link("https://github.com/python33r/kotlin-tour.git")
]

//-------------------------------------------------------------------------//

= Kotlin Basics

== Hello World

Similar to Python (and run a similar way):

```kotlin
println("Hello World!")
```

#pause

(Technically, this is _KotlinScript_ rather than Kotlin...)

#speaker-note[
  We would run this with `kotlin hello.kts`

  But Kotlin isn't really designed to work as a scripting language...
]

== Hello World, Take 2

```kotlin
fun main() {
    println("Hello World!")
}
```

- Less 'scaffolding' than Java
- No need to define classes unless we want them
- No semicolons!

#pause

How do we compile/run this?

== Compiling & Running (JVM)

For this simple case:

#nc[
  ```
  kotlinc hello.kt    # generates HelloKt.class
  kotlin HelloKt
  ```
]

#pause

Multiple `.class` files can be bundled:

#nc[
  ```
  kotlinc -d app.jar main.kt file2.kt file3.kt...
  kotlin -cp app.jar MainKt
  ```
]

#v(.3em)
(or let your IDE / build tool handle it... #emoji.face.wink)

#speaker-note[
  Kotlin's JVM compiler generates Java bytecode, and JVM requires that
  bytecode is organised as classes, so compiler creates this structure
  for us behind the scenes, allowing us to adopt a procedural style
  in source code.

  Demo:
  + Compile `hello.kt` & run program
  + Disassemble with `javap -c HelloKt`

  Second example: `-cp` option sets classpath, just as it would in Java.
]

== Portability

To include the Kotlin runtime library:

#nc[
  ```
  kotlinc -include-runtime -d app.jar main.kt file2.kt...
  kotlin app.jar
  java -jar app.jar   # also works!
  ```
]

#v(0.5em)
#info(title: "Note")[
  This increases the size of the JAR file substantially, but it will
  then be portable to any system with a JVM...
]

== Basic Data Types

- `Char`, `Int`, `Long`, `Float`, `Double`, etc
  - Feel like classes, have methods that we can call
  - Will be represented with primitive types on the JVM

#pause

- Unlike Java, Kotlin has unsigned integers (`UInt`, `UShort`, etc)

#pause

- Kotlin expands Java types using *extension functions*
  - You can write these yourself---e.g., to add your own functionality to
    the `String` class!

#speaker-note[
  Unlike Java, there is no explicit distinction drawn between types
  that have primitive representations and types that are classes.

  Using extension functions is a nicer solution than subclassing a built-in
  type just to add a tiny bit of functionality to it.
]

== Variables

- Introduced using `val` or `var`

- Type can be specified explicitly:

  #nc(left: 1.5em)[```
  val name: String = "Nick"
  var total: Int = 0
  ```]

#pause

- ...or we can exploit *type inference* (preferred):

  #nc(left: 1.5em)[```
  val name = "Nick"
  var total = 0
  ```]

== `val` or `var` ?

- `val` permits assignment _once only_
  - Note: `val` #sym.equiv.not `const`

- `var` permits reassignment, allowing variable to be updated

#pause

#v(0.5em)
#tip(title: "Idiomatic Kotlin", icon: image("kotlin.svg"))[
  Prefer `val` to `var` !
  
  Use `var` only when updating is necessary
]

#speaker-note[
  An object defined with `val` can still change state if it has methods
  that change the values of fields.

  Think of `val` as equivalent to using `final` in Java.
]

== Conditional Execution

- `if`, `else if` and `else` are used very much like they are in Java

- ...but these structures are *expressions* in Kotlin!

  #pad(left: 1.5em, `val largest = if (a > b) a else b`)

- Instead of `switch`, Kotlin has the more powerful `when`

== `when` Expressions

// Need smaller code font from this point on
#show raw.where(lang: "kotlin"): set text(size: 0.9em)

```kotlin
val grade = when (mark) {
    in 70..100 -> "Distinction"
    in 40..69  -> "Pass"
    in 0..39   -> "Fail"
    else       -> throw Exception("Bad mark: $mark")
}
```

#pause

(`else` required to make the expression cover all possibilities---otherwise
its value could be undefined)

#speaker-note[
  Powerful feature, similar to Python's `match`.

  However, `when` in Kotlin can operate as a expression _or_ statement.
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
    What do you think the second `for` example prints?

    If you really want to do more typing, range in second example can also
    be written `1 until 10`.

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

  More on lambdas shortly...
]

== Collections

#table(
  columns: 2,
  inset: 10pt,
  stroke: none,
  [`Pair`], [tuple of two values#footnote[Values can be different types]<tuple>],
  [`Triple`], [tuple of three values#footnote(<tuple>)],
  [`Array`], [fixed-sized collection of primitives or objects],
  [`List`], [ordered collection of objects],
  [`Set`], [unordered collection of unique objects],
  [`Map`], [unordered collection of keys mapped to values]
)

#speaker-note[
  Whilst sets & maps make no guarantees about the ordering of their contents,
  should note that the simplest way of creating a set or map will return
  implementations of these interfaces where order of insertion into the
  collection is preserved.
]

== Creation Patterns

Kotlin provides handy 'factory functions' to create collections:
```kotlin
val fruit = setOf("Apple", "Banana", "Kiwi")
fruit.add("Orange")   // compiler error!
```

#pause

#v(.5em)
If you want mutable collections, you must be explicit:

#codly(highlights: (
  (line: 1, start: 13, end: 19),
))
```kotlin
val fruit = mutableSetOf("Apple", "Banana", "Kiwi")
fruit.add("Orange")   // this is OK
```

== Creation Patterns

Type must be specified if collection is empty:

#codly(highlights: (
  (line: 1, start: 25, end: 32),
))
```kotlin
val data = mutableListOf<Double>()

File("data.txt").forEachLine {
    data.add(it.toDouble())
}

println(data.average())
```

#speaker-note[
  This example shows just one of several ways to read lines of text from
  a file. `File` is the standard class from `java.io` package and
  `forEachLine` is an extension function plugged into the class by Kotlin.

  `forEachLine` expects a lambda function, specifying what we do with each
  line (in this case, parsing to a `Double` and adding to a list).
  
  Note also how easy it is to convert a string to a number, or compute the
  average of a collection of values.
]

== Creation Patterns

Alternatively, if we are OK with getting an immutable list:

#codly(highlights: (
  (line: 1, start: 25, end: 32),
))
```kotlin
val data = buildList {
    File("data.txt").forEachLine {
        add(it.toDouble())
    }
}

println(data.average())
```

#speaker-note[
  This is quite clever. The list that is being created is mutable within
  `buildList` but the returned list is immutable.

  This also looks a little nicer than the previous example.

  (There is also `buildSet` and `buildMap`...)
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
  Element access will throw an exception if the supplied index or key
  is invalid; if you don't want this, use one of the alternatives.

  What do you think this example prints?
]

== Iteration Patterns

#nc[
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

#v(.2em)
```kotlin
println(grade(42))   // prints Pass
```

#speaker-note[
  You always need to specify types in parameter lists.

  You often don't need to specify the return type with expression form, as
  you can rely on type inference.
]

== Functions: Block Form

Here we provide an explicit return type & `return` statement:

#codly(highlights: (
  (line: 1, start: 38, end: 45),
  (line: 6, start: 5, end: 14),
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

Kotlin's *functional programming* support lets us do it in one line!

#pad(left: 1.5em)[`numbers.sumOf { it * it }`]

#pause

#v(.5em)
- `sumOf()` is a method of numeric collections
- It expects a 'selector function' that determines values to be summed
- Selector's only parameter represents a value from collection
- Selector can be a $lambda$ expression
- $lambda$'s parameter has the implicit name '`it`'

#speaker-note[
  This is an example of how concise yet expressive Kotlin can be.

  Lambda expressions have a longer form, but Kotlin allows them to be
  abbreviated in several ways, depending on the context.
]

== More Explicitly...

`numbers.sumOf { it * it }`

is shorthand for

`numbers.sumOf({ value: Double -> value * value })`

#pause

...which is equivalent to

#no-codly[
```
fun square(value: Double) = value * value
numbers.sumOf(square)
```
]

#speaker-note[
  Version with a named function is perhaps clearer, particularly to
  those new to Kotlin, but it is considerably more verbose.
]

== Other Examples

Take a list of integers and generate a new list containing the squares
of only the even values:

#nc(left: 1.5em)[
  ```
  numbers.filter { it % 2 == 0 }
         .map { it * it }
  ```
]

#pause

#v(.5em)
Remove blank strings from a list and organize remaining strings into
groups, keyed by first letter:

#nc(left: 1.5em)[
  ```
  names.filter { it.isNotBlank() }
       .groupBy { it.first() }
  ```
]

#speaker-note[
  Second example returns a map of chars onto lists of strings.

  Many complex operations can be expressed very concisely using this
  functional programming style.

  NOTE: if we convert the lists to *sequences* at the start of a chain of
  ops, then the operations will be efficient because they won't create
  any intermediate lists...
]

== Things We've Not Covered

These are important, but we don't have time to look at them:

- Nullable types
- Scope functions
  - `also`, `apply`, `let`, `run`, `with`
- Extension functions

//-------------------------------------------------------------------------//

= Classes & OOP

== Kotlin vs Java

Consider how we would write a small bank account class with three fields:
ID, holder name, balance (latter being mutable)

#pause

This can require *more than 50 lines of Java* (constructor, getters, setter,
`equals()`, `hashCode()`, `toString()`, etc)

#pause

...or *a single line of Kotlin*:

#nc(left: 1.5em)[
  ```
  data class Account(val id: Int, val name: String,
    var balance: Int)
  ```
]

#speaker-note[
  This conciseness is one of the big reasons why so many Java programmers
  are enthused by Kotlin.

  This is a *data class*. Compiler does extra work for data classes,
  generating `equals()`, `hashCode()` and `toString()` methods for us.

  Recent versions of Java have the `record`, which also allows a one-line
  definition---but with the limitation that records are immutable.

  Demo: compare `Account.java` with `account.kt`, then compile & run both.
]

== A Basic Class

```kotlin
class Person(var name: String, val birth: LocalDate)
```

#pause

- `name` is a writeable *property*, `birth` is a read-only property

#pause

- Properties are abstractions: compiler creates a hidden field, getter
  method and setter method (if needed), which are used implicitly when we
  access a property

#pause

- `Person` is given a two-parameter constructor that initializes the two
  properties to the given values

#speaker-note[
  Normal way of writing a class definition can be a little tricky to
  understand at first because it layers several aspects on top of each
  other.
]

== More Explicitly...

One-liner on previous slide is shorthand for

```kotlin
class Person constructor(_name: String, _birth: LocalDate) {
    var name = _name
    val birth = _birth
}
```

#pause

#question[
  Will Kotlin's conciseness be confusing for students?
]

== Using The Class

```kotlin
fun main() {
    val date = LocalDate.of(1992, 8, 23)
    val p = Person("Joe", birth=date)    // no 'new'

    println(p.name)   // invokes getter method
    p.name = "David"  // invokes setter

    p.birth = LocalDate.of(1995, 8, 23)  // compiler error
}
```

#speaker-note[
  Not seen this before now, but you can specify args to Kotlin methods &
  functions with keyword syntax, as in Python. Here, it makes code clearer.

  Significance of Kotlin using getter and setter methods behind the scenes
  is that these methods can be overridden (see later).
]

== Defining Properties

- We can mix property definitions, putting some in the class header and
  others within the body of the class

- Properties defined in the body must always be initialized---to a default
  value if there is no corresponding constructor parameter

```kotlin
class Person(var name: String, val birth: LocalDate) {
    var isMarried = false
}
```

== Defining Methods

```kotlin
class Person(var name: String, val birth: LocalDate) {

    fun age(): Int {
        val today = LocalDate.now()
        return YEARS.between(birth, today).toInt()
    }

    override fun toString() = "$name, born on $birth"
}
```

== Computed Properties

We can have a more natural `Person` API, in which age is obtained via
property look-up instead of an explicit method call:

```kotlin
class Person(var name: String, val birth: LocalDate) {
    val age: Int
        get() {
            val today = LocalDate.now()
            return YEARS.between(birth, today).toInt()
        }
}
```

#speaker-note[
  Getter is indented to make its association with the `age` property as
  clear as possible, but indentation is not required here.

  Compiler is smart enough to figure out that there is no need to create
  a backing field to store the age of a person.
]

== Example of Use

```kotlin
fun main() {
    val date = LocalDate.of(1992, 8, 23)
    val p = Person("Joe", birth=date)

    println(p.name)   // accesses a field
    println(p.age)    // generates value by computation
}
```

== Overriding Setters

How do we stop someone assigning an invalid name to a `Person`? \
(e.g., an empty string or blank string)

```kotlin
class Person(_name: String, val birth: LocalDate) {
    var name = _name
        set(value) {
            require(value.isNotBlank()) { "Blank name" }
            field = value
        }
}
```

#speaker-note[
  This is where the syntax starts to get less elegant.

  `require` function accepts a boolean expression and throws an exception
  if it is false, using the provided lambda expression to generate the
  error message.

  Note that this _won't_ stop the creation of a `Person` with an invalid
  name, because setter is only used for assignments to the property of an
  already existing object...
]

== Initializer Blocks

We can inject our own code into the construction process:
```kotlin
class Person(_name: String, val birth: LocalDate) {
    init {
        require(_name.isNotBlank()) { "Blank name" }
    }

    var name = _name
        set(value) { ... }
}
```

#place(
  bottom + right,
  dx: -.5em,
  dy: -2em,
  text(size:1.5em, emoji.face.teeth),
)

#speaker-note[
  Now we've completely prevented use of a blank/empty string as a person's
  name, but the solution is not very elegant.

  How would our students take to this? Would they find it confusing?
]

== Alternatively...

#codly(highlights: (
  (line: 2, start: 21, end: 25),
))
```kotlin
class Person(_name: String, val birth: LocalDate) {
    var name = _name.also {
        require(_name.isNotBlank()) { "Blank name" }
    }
    set(value) {
        require(value.isNotBlank()) { "Blank name" }
        field = value
    }
}
```

#speaker-note[
  `also` is one of Kotlin's *scope functions*. It can be used to insert
  additional actions, specified as a lambda, into an operation of
  some kind (in this case, the initialization of a property using a
  constructor argument).

  This is not as 'hacky' as using an `init` block, but the repetition of
  code is a bit annoying.

  Let's look at another example...

  Demo: `rectangle.kt`
]

== Inheritance

- All classes inherit implicitly from `Any` (like `Object` in Java)

- We specify inheritance using `:` rather than `extends`

- A class cannot act as a superclass by default; to enable inheritance
  we must mark it with `open`

- A method in a superclass cannot be overridden by default; to enable
  overriding, we must mark it with `open`

- Properties can be overridden too! (if marked `open` in superclass)

== Example

#align(center, image("shapes.png", width: 80%))

== Example

#codly(highlights: (
  (line: 3, start: 1, end: 4),
  (line: 5, start: 45, end: 56),
))
```kotlin
typealias Coord = Pair<Double,Double>

open class Shape(val position: Coord)

class Circle(pos: Coord, val radius: Double): Shape(pos)

class Rectangle(pos: Coord,
  val width: Double, val height: Double): Shape(pos)
```

#speaker-note[
  Worth stressing again the conciseness of this.

  Four lines of Kotlin give us a hierarchy of one superclass and two
  subclasses, each with fields, constructor and getter methods.
]

== Abstract Classes

#align(center, image("shapes2.png", width: 80%))

#speaker-note[
  Unlike Java, we can have abstract properties that are overridden in
  subclasses.

  In a `Shape` class, properties like area or perimeter have to be
  abstract because we lack the information needed to compute their values.
  That info is supplied in subclasses.

  It makes sense for `Shape` itself to be abstract, regardless.
]

== Abstract Classes

Use `abstract` keyword, as in Java:

```kotlin
abstract class Shape(val position: Coord) {
    abstract val area: Double
}

class Circle(pos: Coord, val radius: Double): Shape(pos) {
    override val area get() = Math.PI * radius * radius
}
```

#speaker-note[
  This code specifies that all shapes must have an `area` property,
  of type `Double`. The `Circle` class implements this as a computed
  property, using the standard circle area formula of $pi r^2$.

  Demo: `shapes2.kt`
]

== Interfaces

#align(center, image("shapes3.png", width: 85%))

== Defining an Interface

Syntax is almost exactly the same as Java:

```kotlin
interface Drawable {
    fun draw()
}
```

- You cannot store state in an interface
- You can implement methods that don't reference stored state
- You can implement computed properties

== Using an Interface

```kotlin
class Circle(pos: Coord, val radius: Double):
  Shape(pos), Drawable {

    override val area get() = Math.PI * radius * radius

    override fun draw() {
        // draw the circle here
    }
}
```

#speaker-note[
  In Java you use the `extends` and `implements` keywords, but in Kotlin
  you just list the superclass and any interfaces after the `:`

  Demo: `shapes3.kt`
]

== Things We've Not Covered

- Nested / inner classes
- Sealed classes
- Enums
- Data classes
- Generic types
- Companion objects
- Creating singletons with `object`
- Operator overloading

#speaker-note[
  We would probably need to cover a few of these if we adopted Kotlin.
]

== Learning More

- #link("https://kotlinlang.org/")[Official website]
  - The official
    #link("https://kotlinlang.org/docs/kotlin-tour-welcome.html")[Kotlin Tour],
    with interactive examples
  - #link("https://play.kotlinlang.org/")[Playground]: run Kotlin in your browser
- Bruce Eckel & Svetlana Isakova, #link("https://www.atomickotlin.com")[_Atomic Kotlin_]
- Dave Leeds, #link("https://typealias.com/start/")[_Kotlin: An Illustrated Guide_]
- Venkat Subramaniam,
  #link("https://pragprog.com/titles/vskotlin/programming-kotlin/")[_Programming Kotlin_]
- Dawn Griffiths & David Griffiths,
  #link("https://www.oreilly.com/library/view/head-first-kotlin/9781491996683/")[_Head-First Kotlin_]
