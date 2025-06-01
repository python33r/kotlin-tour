import io.ktor.server.application.Application

fun Application.module() {
    configureRouting()
}

fun main(args: Array<String>) {
    io.ktor.server.jetty.jakarta.EngineMain.main(args)
}
