import ua.edmko.buildsrc.Database

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
    implementation(project(":domain"))

    kapt(Database.compiler)
    implementation(Database.extensions)
    implementation(Database.runtime)

    implementation(ua.edmko.buildsrc.gson)
    implementation("javax.annotation:jsr250-api:1.0")
    implementation("javax.inject:javax.inject:1")
    implementation(ua.edmko.buildsrc.HiltDependencies.hilt)
    kapt(ua.edmko.buildsrc.HiltDependencies.kaptHilt)
}