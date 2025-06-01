import io.ktor.http.ContentType
import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    val type = ContentType.parse("text/html")
    routing {
        get("/") {
            call.respondText("<p>Hello World!</p>", type)
        }
        get("/hello/{name}") {
            val name = call.parameters["name"]
            call.respondText("<p>Hello $name!</p>", type)
        }
    }
}
