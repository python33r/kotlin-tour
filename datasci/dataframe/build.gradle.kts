plugins {
    kotlin("jvm") version "2.0.20"
    id("org.jetbrains.kotlinx.dataframe") version "0.15.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:dataframe:0.15.0")
    runtimeOnly("org.slf4j:slf4j-nop:2.0.16")
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "MainKt"
}
