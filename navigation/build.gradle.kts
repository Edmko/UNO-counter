plugins {
    id("compose-config")
}

android {
    namespace = "ua.edmko.navigation"
}

dependencies {
    implementation(ua.edmko.buildsrc.Navigation.compose)
    implementation("javax.annotation:jsr250-api:1.0")
    implementation("javax.inject:javax.inject:1")
    implementation(ua.edmko.buildsrc.Coroutines.core)
    implementation(project(":core"))
    implementation(project(":features:endgame"))
    implementation(project(":features:game"))
    implementation(project(":features:players"))
    implementation(project(":features:setup"))
    implementation(project(":features:privacy"))
}
