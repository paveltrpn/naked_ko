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
}

application {
    mainClass = "naked.MainKt"
}
