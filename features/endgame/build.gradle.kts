import ua.edmko.buildsrc.AndroidLibraries
import ua.edmko.buildsrc.HiltDependencies

plugins {
    id("compose-config")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":core-ui"))
}