import ua.edmko.buildsrc.*

plugins {
    id("dagger.hilt.android.plugin")
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = AndroidSdk.compile

    defaultConfig {
        applicationId = "ua.edmko.unocounter"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.compile
        versionCode = 2
        versionName = "1.0"
    }
    
    signingConfigs {
        create("release") {
            storeFile = file("C:\\Users\\Edmko\\upload_edmko.jks")
            storePassword = "s42igL3Tk9LXi8A"
            keyAlias = "key0"
            keyPassword = "s42igL3Tk9LXi8A"
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isShrinkResources = true
            isMinifyEnabled = true
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
    implementation(project(":core-ui"))
    implementation(project(":features:endgame"))
    implementation(project(":features:game"))
    implementation(project(":features:players"))
    implementation(project(":features:setup"))
    implementation(project(":features:privacy"))

    implementation(AndroidLibraries.core)
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.materialCore)
    implementation(AndroidLibraries.splash)

    implementation(LifecycleLibraries.vmKtx)
    implementation(LifecycleLibraries.runtime)
    implementation(LifecycleLibraries.vmCompose)
    implementation(gson)
    implementation(ComposeLibraries.ui)
    implementation(ComposeLibraries.uiTooling)
    implementation(ComposeLibraries.foundation)
    implementation(ComposeLibraries.foundationLayout)
    implementation(ComposeLibraries.material)
    implementation(ComposeLibraries.iconsCore)
    implementation(ComposeLibraries.iconExtended)
    implementation(ComposeLibraries.constraint)

    kapt(HiltDependencies.kaptHilt)
    implementation(HiltDependencies.hilt)
    implementation(HiltDependencies.hiltNavigation)

    kapt(Database.compiler)
    implementation(Database.extensions)
    implementation(Database.runtime)

    implementation(Navigation.compose)

    implementation(Coroutines.android)
    implementation(Coroutines.core)

    coreLibraryDesugaring(desugarJdk)
}