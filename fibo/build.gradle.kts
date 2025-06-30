plugins {
    application
    kotlin("jvm") version "2.2.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":modules"))
}

application {
    mainClass = "fibo.FiboKt"
}

