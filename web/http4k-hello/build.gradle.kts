plugins {
    kotlin("jvm") version "2.1.10"
    application
}

repositories {
    mavenCentral()
}

val http4kVersion = "6.10.0.0"
val kotestVersion = "5.9.1"

dependencies {
    implementation(platform("org.http4k:http4k-bom:$http4kVersion"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-jetty")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    testImplementation("org.http4k:http4k-testing-kotest")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

application {
    mainClass = "MainKt"
}
