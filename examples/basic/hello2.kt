// 'Hello World' with command line arguments and string interpolation

fun main(args: Array<String>) {
    if (args.size > 0) {
        println("Greetings, ${args[0]}!")
    }
    else {
        println("Hello World!")
    }
}
