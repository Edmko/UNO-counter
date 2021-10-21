import ua.edmko.buildsrc.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "ua.edmko.unocounter"
        minSdk = 24
        targetSdk = 31
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
        isCoreLibraryDesugaringEnabled = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ComposeLibraries.Versions.compose
    }
}

dependencies {
    implementation(project(":navigation"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":core"))
    implementation(project(":features:endgame"))
    implementation(project(":features:game"))
    implementation(project(":features:players"))
    implementation(project(":features:setup"))

    implementation(AndroidLibraries.core)
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.materialCore)

    implementation(LifecycleLibraries.vmKtx)
    implementation(LifecycleLibraries.runtime)
    implementation(LifecycleLibraries.vmCompose)
    implementation(gson)
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

    coreLibraryDesugaring(desugarJdk)
}