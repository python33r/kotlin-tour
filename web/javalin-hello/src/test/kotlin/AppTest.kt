import io.javalin.testtools.JavalinTest.test
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

@Suppress("unused")
class AppTest: StringSpec({
    isolationMode = IsolationMode.InstancePerTest

    val app = Hello().app

    "/ yields a 'Hello World' greeting" {
        test(app) { _, client ->
            val response = client.get("/")
            response.code shouldBe 200
            response.headers["Content-Type"] shouldBe "text/html"
            response.body?.string() shouldContain "<p>Hello World!</p>"
        }
    }

    "/hello yields a personalized greeting" {
        test(app) { _, client ->
            val response = client.get("/hello/Nick")
            response.code shouldBe 200
            response.headers["Content-Type"] shouldBe "text/html"
            response.body?.string() shouldContain "<p>Hello Nick</p>"
        }
    }
})
