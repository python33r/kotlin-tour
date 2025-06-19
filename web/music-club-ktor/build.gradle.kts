plugins {
    kotlin("jvm") version "2.1.21"
    id ("io.ktor.plugin") version "3.2.0"
}

repositories {
    mavenCentral()
}

val logbackVersion = "1.5.13"
val sqliteJdbcVersion = "3.50.1.0"
val exposedVersion = "0.61.0"
val kotestVersion = "5.9.1"

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-jetty-jakarta")
    implementation("io.ktor:ktor-server-config-yaml")
    implementation("io.ktor:ktor-server-pebble")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.xerial:sqlite-jdbc:$sqliteJdbcVersion")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.ktor:ktor-server-test-host")
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "io.ktor.server.jetty.jakarta.EngineMain"
}

tasks.named<JavaExec>("run") {
    description = "Runs the web application."
}

tasks.register<JavaExec>("createdb") {
    group = "application"
    description = "Creates the database."
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass = "CreateDbKt"
}

tasks.register<JavaExec>("query") {
    group = "application"
    description = "Runs some queries against the database."
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass = "QueryKt"
}
