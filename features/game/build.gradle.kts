plugins {
    id("compose-config")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":core-ui"))
}
android {
    namespace = "ua.edmko.game"
}
