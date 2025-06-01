import org.http4k.core.then
import org.http4k.filter.DebuggingFilters
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main() {
    //val app = helloApp()
    val app = DebuggingFilters.PrintRequestAndResponse().then(helloApp())
    val server = app.asServer(Jetty(7070))
    server.start()
}
