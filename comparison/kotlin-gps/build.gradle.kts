plugins {
    kotlin("jvm") version "2.1.21"
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
    application
}

repositories {
    mavenCentral()
}

val kotestVersion = "5.9.1"

dependencies {
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

detekt {
    toolVersion = "1.23.8"
}

application {
    mainClass = "TrackInfoKt"
}

tasks.named<JavaExec>("run") {
    args = listOf("data/walk.csv")
}
