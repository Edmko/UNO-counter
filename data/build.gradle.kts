import ua.edmko.buildsrc.Database

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk = 24
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
}