import io.javalin.Javalin

class Hello {
    val app: Javalin = Javalin.create()
        .get("/") {
            it.html("<p>Hello World!</p>")
        }
        .get("/hello/{name}") {
            val name = it.pathParam("name")
            it.html("<p>Hello $name</p>")
        }
}
