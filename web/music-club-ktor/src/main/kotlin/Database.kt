import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase() {
    Database.connect("jdbc:sqlite:file:music.db")
}
