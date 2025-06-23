plugins {
    kotlin("jvm") version "2.1.21"
    application
}

repositories {
    mavenCentral()
}

val javalinVersion = "6.6.0"
val exposedVersion = "0.61.0"

dependencies {
    implementation("io.javalin:javalin:$javalinVersion")
    implementation("io.javalin:javalin-rendering:$javalinVersion")
    implementation("io.pebbletemplates:pebble:3.1.6")
    // note: Pebble version can't be upgraded yet due to a Javalin dependency
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("org.xerial:sqlite-jdbc:3.50.1.0")
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

tasks.named<JavaExec>("run") {
    description = "Runs the web application."
}

tasks.register<JavaExec>("createdb") {
    group = "application"
    description = "Creates the database."
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass = "DatabaseKt"
}

tasks.register<JavaExec>("querydemo") {
    group = "application"
    description = "Runs some queries against the database."
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass = "QueryDemoKt"
}
