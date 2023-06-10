plugins {
    id("compose-config")
}

dependencies {
    implementation(project(mapOf("path" to ":navigation")))
}