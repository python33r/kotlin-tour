// Routing and application logic for Music Club app
// (see also the templates in src/main/resources/templates)

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.pebble.respondTemplate
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

fun Application.configureRouting() {
    routing {
        get("/") {
            newSuspendedTransaction {
                call.respondTemplate("index.html", mapOf(
                    "albums" to Album.count(),
                    "artists" to Artist.count(),
                ))
            }
        }

        post("/search") {
            newSuspendedTransaction {
                val formParams = call.receiveParameters()
                val searchTerm = formParams["search_term"] ?: ""
                val results = Album.find { Albums.title like "%$searchTerm%" }
                call.respondTemplate("search.html", mapOf(
                    "searchTerm" to searchTerm,
                    "results" to results,
                ))
            }
        }

        get("/artists") {
            newSuspendedTransaction {
                val artists = Artist.all().sortedBy { it.name }.toList()
                call.respondTemplate("artists.html", mapOf("artists" to artists))
            }
        }

        get("/artists/{id}") {
            newSuspendedTransaction {
                val artist = call.parameters["id"]?.let {
                    Artist.findById(it.toUInt())
                }
                when (artist) {
                    null -> call.respond(HttpStatusCode.NotFound)
                    else -> {
                        val albums = artist.albums.sortedBy { it.year }.toList()
                        call.respondTemplate("artist.html", mapOf(
                            "name" to artist.name,
                            "solo" to artist.isSolo,
                            "info" to (artist.info ?: ""),
                            "albums" to albums,
                        ))
                    }
                }
            }
        }

        get("/albums") {
            newSuspendedTransaction {
                val comparator = compareBy<Album> { it.artist.name }.thenBy { it.year }
                val albums = Album.all().sortedWith(comparator).toList()
                call.respondTemplate("albums.html", mapOf("albums" to albums))
            }
        }

        get("/albums/{id}") {
            newSuspendedTransaction {
                val album = call.parameters["id"]?.let {
                    Album.findById(it.toUInt())
                }
                when (album) {
                    null -> call.respond(HttpStatusCode.NotFound)
                    else -> call.respondTemplate("album.html", mapOf(
                        "artist" to album.artist,
                        "title" to album.title,
                        "year" to album.year,
                        "youtube" to (album.youtube ?: "")
                    ))
                }
            }
        }
    }
}
