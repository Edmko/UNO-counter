import ua.edmko.buildsrc.webkit

plugins {
    id("compose-config")
}

dependencies {
    implementation(project(":core-ui"))
    implementation(webkit)
}
android {
    namespace = "ua.edmko.privacy"
}
