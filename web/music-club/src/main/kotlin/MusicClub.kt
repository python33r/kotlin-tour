// Music Club web application

import io.javalin.Javalin
import io.javalin.rendering.template.JavalinPebble
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

object MusicClub {
    val app: Javalin = Javalin.create {
        it.fileRenderer(JavalinPebble())
    }

    init {
        Database.connect("jdbc:sqlite:file:music.db")

        app.get("/") {
            transaction {
                it.render("templates/index.html", mapOf(
                    "albums" to Album.count(),
                    "artists" to Artist.count(),
                ))
            }
        }

        app.post("/search") {
            transaction {
                val searchTerm = it.formParam("search_term")
                val results = Album.find { Albums.title like "%$searchTerm%" }
                it.render("templates/search.html", mapOf(
                    "searchTerm" to searchTerm,
                    "results" to results,
                ))
            }
        }

        app.get("/artists") {
            transaction {
                val artists = Artist.all().sortedBy { it.name }.toList()
                it.render("templates/artists.html", mapOf("artists" to artists))
            }
        }

        app.get("/artists/{id}") {
            transaction {
                val result = runCatching {
                    val id = it.pathParam("id").toUInt()
                    Artist.findById(id)
                }

                val artist = result.getOrNull()

                val data = mapOf(
                    "name" to artist?.name,
                    "solo" to artist?.isSolo,
                    "info" to artist?.info,
                    "albums" to artist?.albums?.sortedBy { it.year }?.toList()
                )
                it.render("templates/artist.html", data)
            }
        }

        app.get("/albums") {
            transaction {
                val comparator = compareBy<Album> { it.artist.name }.thenBy { it.year }
                val albums = Album.all().sortedWith(comparator).toList()
                it.render("templates/albums.html", mapOf("albums" to albums))
            }
        }

        app.get("/albums/{id}") {
            transaction {
                val result = runCatching {
                    val id = it.pathParam("id").toUInt()
                    Album.findById(id)
                }

                val album = result.getOrNull()

                val data = mapOf(
                    "artist" to album?.artist,
                    "title" to album?.title,
                    "year" to album?.year,
                    "youtube" to album?.youtube,
                )
                it.render("templates/album.html", data)
            }
        }
    }
}
