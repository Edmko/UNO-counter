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
        kotlinCompilerExtensionVersion = ua.edmko.buildsrc.ComposeLibraries.Versions.compose
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":core-ui"))
    implementation(ua.edmko.buildsrc.AndroidLibraries.core)
    implementation(ua.edmko.buildsrc.AndroidLibraries.appCompat)
    implementation(ua.edmko.buildsrc.AndroidLibraries.materialCore)



    kapt(ua.edmko.buildsrc.HiltDependencies.kaptHilt)
    implementation(ua.edmko.buildsrc.HiltDependencies.hilt)
    implementation(ua.edmko.buildsrc.HiltDependencies.hiltNavigation)
}