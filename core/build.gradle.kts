plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 24
        targetSdk = 31
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
    implementation(ua.edmko.buildsrc.AndroidLibraries.core)
    implementation(ua.edmko.buildsrc.AndroidLibraries.appCompat)
    implementation(ua.edmko.buildsrc.AndroidLibraries.materialCore)
    implementation(ua.edmko.buildsrc.ComposeLibraries.ui)
    implementation(ua.edmko.buildsrc.ComposeLibraries.uiTooling)
    implementation(ua.edmko.buildsrc.ComposeLibraries.foundation)
    implementation(ua.edmko.buildsrc.ComposeLibraries.foundationLayout)
    implementation(ua.edmko.buildsrc.ComposeLibraries.material)
    implementation(ua.edmko.buildsrc.ComposeLibraries.iconsCore)
    implementation(ua.edmko.buildsrc.ComposeLibraries.iconExtended)
    implementation(ua.edmko.buildsrc.ComposeLibraries.constraint)
    implementation(project(mapOf("path" to ":navigation")))

}