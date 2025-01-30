plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.hamcrest:hamcrest:2.2")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
    }
}

tasks.test {
    testLogging {
        events("passed", "skipped", "failed")
    }
}

application {
    mainClass = "TrackInfo"
}

tasks.named<JavaExec>("run") {
    args = listOf("data/walk.csv")
}
