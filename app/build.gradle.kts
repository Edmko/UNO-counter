import ua.edmko.buildsrc.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id ("dagger.hilt.android.plugin")
}

android {
    compileSdk = AndroidSdk.compile

    defaultConfig {
        applicationId = "ua.edmko.unocounter"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.compile
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta05"
    }
}

dependencies {

    implementation(AndroidLibraries.core)
    implementation(AndroidLibraries.appCompat)

    implementation(LifecycleLibraries.viewModel)
    implementation(LifecycleLibraries.runtime)
    implementation(LifecycleLibraries.liveData)

    implementation(ComposeLibraries.ui)
    implementation(ComposeLibraries.uiTooling)
    implementation(ComposeLibraries.foundation)
    implementation(ComposeLibraries.foundationLayout)
    implementation(ComposeLibraries.material)
    implementation(ComposeLibraries.materialIconsCore)
    implementation(ComposeLibraries.materialIconsExtended)
    implementation(ComposeLibraries.liveData)
    implementation(ComposeLibraries.activity)
    implementation(ComposeLibraries.lifecycleViewModel)
    implementation(ComposeLibraries.constraint)

    kapt(HiltDependencies.kaptHilt)
    kapt(HiltDependencies.kaptLifecycle)
    implementation(HiltDependencies.hilt)
    implementation(HiltDependencies.lifecycle)
    implementation(HiltDependencies.hiltNavigation)

    implementation(Navigation.compose)
    implementation(Navigation.ktx)

    implementation(Coroutines.android)
    implementation(Coroutines.core)
}