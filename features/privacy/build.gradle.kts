plugins {
    alias(libs.plugins.plugin.feature.compose)
}

android {
    namespace = "ua.edmko.privacy"
}

dependencies {
    implementation(libs.webkit)
}
