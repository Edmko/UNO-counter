plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
    api(ua.edmko.buildsrc.ComposeLibraries.ui)
    api(ua.edmko.buildsrc.ComposeLibraries.uiTooling)
    api(ua.edmko.buildsrc.ComposeLibraries.foundation)
    api(ua.edmko.buildsrc.ComposeLibraries.foundationLayout)
    api(ua.edmko.buildsrc.ComposeLibraries.material)
    api(ua.edmko.buildsrc.ComposeLibraries.iconsCore)
    api(ua.edmko.buildsrc.ComposeLibraries.iconExtended)
    api(ua.edmko.buildsrc.ComposeLibraries.constraint)
    api(ua.edmko.buildsrc.ComposeLibraries.systemUiController)
    implementation(ua.edmko.buildsrc.AndroidLibraries.core)
    implementation(ua.edmko.buildsrc.AndroidLibraries.appCompat)

    ////workaround to show preview. it will be fixed in AS Dolphin
    debugApi ("androidx.customview:customview:1.2.0-alpha01")
    debugApi("androidx.customview:customview-poolingcontainer:1.0.0-beta02")
}