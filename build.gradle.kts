plugins {
    kotlin("jvm") version "2.1.0"
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}