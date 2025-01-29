plugins {
    kotlin("jvm") version "2.1.0"
    application
}

repositories {
    mavenCentral()
}

val javalinVersion = "6.4.0"
val exposedVersion = "0.58.0"

dependencies {
    implementation("io.javalin:javalin:$javalinVersion")
    implementation("io.javalin:javalin-rendering:$javalinVersion")
    implementation("io.pebbletemplates:pebble:3.1.6")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("org.xerial:sqlite-jdbc:3.48.0.0")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "AppMainKt"
}

tasks.register<JavaExec>("createdb") {
    group = "application"
    description = "Creates the database"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass = "CreateDbKt"
}

tasks.register<JavaExec>("query") {
    group = "application"
    description = "Runs some queries against the database"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass = "QueryKt"
}
