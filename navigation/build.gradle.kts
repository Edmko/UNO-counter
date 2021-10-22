plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk= 24
    }
}

dependencies{
    implementation(ua.edmko.buildsrc.Navigation.compose)
    implementation("javax.annotation:jsr250-api:1.0")
    implementation("javax.inject:javax.inject:1")
    implementation(ua.edmko.buildsrc.coroutines)
}