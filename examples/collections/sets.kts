// Use of sets in Kotlin

val names = mutableSetOf<String>()
println("Created instance of ${names::class}...")

println("\nInitially:")
println(names)
println("Size = ${names.size}")
println("Empty? ${names.isEmpty()}")

names.add("Alice")
names.add("Bob")
names.add("Charlie")
names.add("David")
names.add("David")

println("\nAfter insertions:")
println(names)
println("Size = ${names.size}")
println("Empty? ${names.isEmpty()}")

names.remove("Bob")
println("\nAfter removal:")
println(names)

println("\nIteration over contents:")
for (name in names) {
    println(name)
}

/*
  Loop above can also be written in a functional style:

    names.forEach { println(it) }
*/
