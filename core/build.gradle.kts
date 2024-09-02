plugins {
    alias(libs.plugins.plugin.android.library)
}

android {
    namespace = "ua.edmko.core"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    api(libs.androidx.compose.material)
    implementation(libs.navigation.compose)
}
