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
}