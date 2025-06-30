plugins {
    application
    kotlin("jvm") version "2.2.0"
}

dependencies {
    implementation(project(":modules"))
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "naked.MainKt"
}
