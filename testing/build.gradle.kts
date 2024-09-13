plugins {
    alias(libs.plugins.plugin.android.library)
}

android {
    namespace = "ua.edmko.testing"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core"))
    api(libs.androidx.compose.ui.test.junit)
    debugApi(libs.androidx.compose.ui.test.manifest)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.mockito.kotlin)
}
