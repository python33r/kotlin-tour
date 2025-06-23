// Tests for Music Club application
// (Note: these are meant to illustrate the general approach;
// they are NOT meant as good examples of what you should test!)

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.formUrlEncode
import io.ktor.server.testing.testApplication

@Suppress("unused")
class ApplicationTest: StringSpec({

    createTestDatabase()   // runs once

    "Path / yields summary page with search form" {
        testApplication {
            application { testModule() }
            val response = client.get("/")
            response.status shouldBe HttpStatusCode.OK
            response.headers["Content-Type"]?.shouldContain("text/html")
            response.bodyAsText() shouldContain "2 artists"
            response.bodyAsText() shouldContain "3 albums"
            response.bodyAsText() shouldContain "<form role=\"search\""
        }
    }

    "Search yields matching albums" {
        testApplication {
            application { testModule() }
            val response = client.post("/search") {
                header(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
                setBody(listOf("search_term" to "second").formUrlEncode())
            }
            response.status shouldBe HttpStatusCode.OK
            response.headers["Content-Type"]?.shouldContain("text/html")
            response.bodyAsText() shouldNotContain "An Album"
            response.bodyAsText() shouldNotContain "First Album"
            response.bodyAsText() shouldContain "Second Album"
        }
    }

    "Path /artists yields list of artists" {
        testApplication {
            application { testModule() }
            val response = client.get("/artists")
            response.status shouldBe HttpStatusCode.OK
            response.headers["Content-Type"]?.shouldContain("text/html")
            response.bodyAsText() shouldContain "A Band"
            response.bodyAsText() shouldContain "Doe, John"
        }
    }

    "Path /artists/1 yields individual artist details" {
        testApplication {
            application { testModule() }
            val response = client.get("/artists/1")
            response.status shouldBe HttpStatusCode.OK
            response.headers["Content-Type"]?.shouldContain("text/html")
            response.bodyAsText() shouldContain "<h2>A Band</h2>"
        }
    }

    "Path /artists/3 yields Page Not Found error" {
        testApplication {
            application { testModule() }
            val response = client.get("/artists/3")
            response.status shouldBe HttpStatusCode.NotFound
        }
    }

    "Path /albums yields table of albums" {
        testApplication {
            application { testModule() }
            val response = client.get("/albums")
            response.status shouldBe HttpStatusCode.OK
            response.headers["Content-Type"]?.shouldContain("text/html")
            response.bodyAsText() shouldContain "An Album"
            response.bodyAsText() shouldContain "First Album"
            response.bodyAsText() shouldContain "Second Album"
        }
    }

    "Path /albums/1 yield individual album details" {
        testApplication {
            application { testModule() }
            val response = client.get("/albums/1")
            response.status shouldBe HttpStatusCode.OK
            response.headers["Content-Type"]?.shouldContain("text/html")
            response.bodyAsText() shouldContain "<h2>An Album</h2>"
            response.bodyAsText() shouldContain "<p>Released in 2025</p>"
        }
    }

    "Path /albums/4 yields Page Not Found error" {
        testApplication {
            application { testModule() }
            val response = client.get("/albums/4")
            response.status shouldBe HttpStatusCode.NotFound
        }
    }
})
