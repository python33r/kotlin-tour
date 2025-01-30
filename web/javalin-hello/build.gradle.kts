plugins {
    kotlin("jvm") version "2.1.10"
    application
}

repositories {
    mavenCentral()
}

val javalinVersion = "6.4.0"
val kotestVersion = "5.9.0"

dependencies {
    implementation("io.javalin:javalin:$javalinVersion")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    testImplementation("io.javalin:javalin-testtools:$javalinVersion")
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
