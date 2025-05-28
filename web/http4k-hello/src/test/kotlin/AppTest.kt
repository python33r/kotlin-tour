import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.contain
import org.http4k.core.ContentType
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.http4k.kotest.shouldHaveBody
import org.http4k.kotest.shouldHaveContentType
import org.http4k.kotest.shouldHaveStatus

@Suppress("unused")
class AppTest: StringSpec({
    val app = helloApp()

    "Path of / yields 'Hello World!' response" {
        val response = app(Request(GET, "/"))
        response shouldHaveStatus OK
        response shouldHaveContentType ContentType.TEXT_HTML
        response shouldHaveBody(contain("<p>Hello World!</p>"))
    }

    "Path of /hello/Nick yields 'Hello Nick!' response" {
        val response = app(Request(GET, "/hello/Nick"))
        response shouldHaveStatus OK
        response shouldHaveContentType ContentType.TEXT_HTML
        response shouldHaveBody(contain("<p>Hello Nick!</p>"))
    }
})
