// Demo showing that val doesn't make objects immutable

class Dog(var name: String)

val dog = Dog("Fido")   // dog defined using val
println(dog.name)

dog.name = "Rover"      // ...yet we can change a property of the object!
println(dog.name)
