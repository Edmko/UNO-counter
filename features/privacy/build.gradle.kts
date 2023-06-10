import ua.edmko.buildsrc.webkit

plugins {
    id("compose-config")
}

dependencies {
    implementation(project(":core-ui"))
    implementation(webkit)
}