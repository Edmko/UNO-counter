plugins {
    alias(libs.plugins.plugin.android.library)
    alias(libs.plugins.plugin.compose)
}
android {
    namespace = "ua.edmko.core.ui"
}

dependencies{
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    api(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.material.icons.extended)
}
