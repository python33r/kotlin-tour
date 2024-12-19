// Example of property validation on instantiation

class Person(val name: String) {
  init {
    require(!name.isBlank()) { "name cannot be blank or empty" }
  }

  var isMarried = false
}

fun test(name: String) {
  println("\nAttempting to create a Person named \"$name\"...")
  val person = Person(name)
  println("Person's name is ${person.name}")
}

test("Joe")
test("")      // should produce an exception
