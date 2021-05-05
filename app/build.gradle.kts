import ua.edmko.buildsrc.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "ua.edmko.unocounter"
        minSdk = 24
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ComposeLibraries.Versions.compose
    }
}

dependencies {

    implementation(AndroidLibraries.core)
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.materialCore)

    implementation(LifecycleLibraries.viewModel)
    implementation(LifecycleLibraries.runtime)

    implementation(ComposeLibraries.ui)
    implementation(ComposeLibraries.uiTooling)
    implementation(ComposeLibraries.foundation)
    implementation(ComposeLibraries.foundationLayout)
    implementation(ComposeLibraries.material)
    implementation(ComposeLibraries.materialIconsCore)
    implementation(ComposeLibraries.materialIconsExtended)
    implementation(ComposeLibraries.activity)
    implementation(ComposeLibraries.lifecycleViewModel)
    implementation(ComposeLibraries.constraint)

    kapt(HiltDependencies.kaptHilt)
    kapt(HiltDependencies.compiler)
    implementation(HiltDependencies.hilt)
    implementation(HiltDependencies.lifecycle)
    implementation(HiltDependencies.hiltNavigation)

    kapt(Database.compiler)
    implementation(Database.extensions)
    implementation(Database.runtime)

    implementation(Navigation.compose)

    implementation(Coroutines.android)
    implementation(Coroutines.core)
}