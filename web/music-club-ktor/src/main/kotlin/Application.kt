// Application configuration & entry point

import io.ktor.server.application.Application

fun Application.module() {
    connectToDatabase()
    configureTemplates()
    configureRouting()
}

fun Application.testModule() {
    connectToTestDatabase()
    configureTemplates()
    configureRouting()
}

fun main(args: Array<String>) {
    io.ktor.server.jetty.jakarta.EngineMain.main(args)
}
