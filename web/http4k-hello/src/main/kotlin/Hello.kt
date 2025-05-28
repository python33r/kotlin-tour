import org.http4k.core.ContentType
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.contentType
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes

fun helloApp(): HttpHandler {
    return routes(
        "/" bind GET to {
            Response(OK)
                .contentType(ContentType.TEXT_HTML)
                .body("<p>Hello World!</p>")
        },
        "/hello/{name}" bind GET to {
            val name = it.path("name")
            Response(OK)
                .contentType(ContentType.TEXT_HTML)
                .body("<p>Hello $name!</p>")
        }
    )
}
