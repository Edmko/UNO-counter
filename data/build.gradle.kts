plugins {
    alias(libs.plugins.plugin.android.library)
    alias(libs.plugins.plugin.hilt)
}

android {
    namespace = "ua.edmko.data"
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.gson)
}
