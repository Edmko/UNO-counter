plugins {
    alias(libs.plugins.plugin.android.library)
    alias(libs.plugins.plugin.hilt)
    alias(libs.plugins.plugin.compose)
}

android {
    namespace = "ua.edmko.navigation"
}

dependencies {
    implementation(libs.jsr250.api)
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.navigation.compose)
    implementation(project(":core"))
    implementation(project(":features:endgame"))
    implementation(project(":features:game"))
    implementation(project(":features:players"))
    implementation(project(":features:setup"))
    implementation(project(":features:privacy"))
}
