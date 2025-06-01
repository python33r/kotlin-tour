import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication

@Suppress("unused")
class ApplicationTest: StringSpec({
    "Path / yields 'Hello World!' greeting" {
        testApplication {
            application { module() }
            val response = client.get("/")
            response.status shouldBe HttpStatusCode.OK
            response.headers["Content-Type"]?.shouldContain("text/html")
            response.bodyAsText() shouldContain "<p>Hello World!</p>"
        }
    }

    "Path /hello/Nick yields 'Hello Nick!' greeting" {
        testApplication {
            application { module() }
            val response = client.get("/hello/Nick")
            response.status shouldBe HttpStatusCode.OK
            response.headers["Content-Type"]?.shouldContain("text/html")
            response.bodyAsText() shouldContain "<p>Hello Nick!</p>"
        }
    }

    "Path /hello yields Page Not Found error" {
        testApplication {
            application { module() }
            val response = client.get("/hello")
            response.status shouldBe HttpStatusCode.NotFound
        }
    }
})
