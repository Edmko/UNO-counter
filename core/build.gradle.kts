plugins {
    id("compose-config")
}

dependencies {
    implementation(project(mapOf("path" to ":navigation")))
}
android {
    namespace = "ua.edmko.core"
}
