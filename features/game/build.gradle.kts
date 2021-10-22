import ua.edmko.buildsrc.AndroidLibraries
import ua.edmko.buildsrc.ComposeLibraries

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
        kotlinCompilerExtensionVersion = ComposeLibraries.Versions.compose
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))

    implementation(AndroidLibraries.core)
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.materialCore)

    implementation(ComposeLibraries.accompanist)
    implementation(ComposeLibraries.ui)
    implementation(ComposeLibraries.uiTooling)
    implementation(ComposeLibraries.foundation)
    implementation(ComposeLibraries.foundationLayout)
    implementation(ComposeLibraries.material)
    implementation(ComposeLibraries.iconsCore)
    implementation(ComposeLibraries.iconExtended)
    implementation(ComposeLibraries.activity)
    implementation(ComposeLibraries.constraint)

    kapt(ua.edmko.buildsrc.HiltDependencies.kaptHilt)
    kapt(ua.edmko.buildsrc.HiltDependencies.compiler)
    implementation(ua.edmko.buildsrc.HiltDependencies.hilt)
    implementation(ua.edmko.buildsrc.HiltDependencies.lifecycle)
    implementation(ua.edmko.buildsrc.HiltDependencies.hiltNavigation)
}