// Application configuration & entry point

import io.ktor.server.application.Application

fun Application.module() {
    configureDatabase()
    configureTemplates()
    configureRouting()
}

fun main(args: Array<String>) {
    io.ktor.server.jetty.jakarta.EngineMain.main(args)
}
