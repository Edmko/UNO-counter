plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = ua.edmko.buildsrc.AndroidSdk.compile

    defaultConfig {
        minSdk = ua.edmko.buildsrc.AndroidSdk.min
        targetSdk = ua.edmko.buildsrc.AndroidSdk.compile
    }
}

dependencies {
    implementation(ua.edmko.buildsrc.Navigation.compose)
    implementation("javax.annotation:jsr250-api:1.0")
    implementation("javax.inject:javax.inject:1")
    implementation(ua.edmko.buildsrc.Coroutines.core)
}