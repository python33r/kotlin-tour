import org.http4k.core.ContentType
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.contentType
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes

fun okResponse() = Response(OK).contentType(ContentType.TEXT_HTML)

val homePage = { request: Request ->
    okResponse().body("<p>Hello World!</p>")
}

val greetingPage = { request: Request ->
    val name = request.path("name")
    okResponse().body("<p>Hello $name!</p>")
}

fun helloApp() = routes(
    "/" bind GET to homePage,
    "/hello/{name}" bind GET to greetingPage,
)
