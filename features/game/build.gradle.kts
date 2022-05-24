import ua.edmko.buildsrc.AndroidLibraries
import ua.edmko.buildsrc.AndroidSdk
import ua.edmko.buildsrc.ComposeLibraries

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = AndroidSdk.compile

    defaultConfig {
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.compile
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ComposeLibraries.Versions.compose
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":core-ui"))
    implementation(AndroidLibraries.core)
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.materialCore)

    kapt(ua.edmko.buildsrc.HiltDependencies.kaptHilt)
    implementation(ua.edmko.buildsrc.HiltDependencies.hilt)
    implementation(ua.edmko.buildsrc.HiltDependencies.hiltNavigation)
}