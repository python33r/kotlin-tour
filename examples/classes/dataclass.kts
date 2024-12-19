// This program produces different output depending on whether
// Person is defined as a regular class or a data class

class Person(val name: String, var isMarried: Boolean)

val p1 = Person("Joe", true)
val p2 = Person("Joe", true)

println(p1)         // output changes when Person is a data class
println(p1 == p2)   // output changes when Person is a data class
println(p1 === p2)  // always returns false: p1 & p2 are different objects

// Code below works only if Person is a data class
//val p3 = p1.copy()
//println(p3)
