import ua.edmko.buildsrc.Analytics
import ua.edmko.buildsrc.AndroidLibraries
import ua.edmko.buildsrc.AndroidSdk
import ua.edmko.buildsrc.ComposeLibraries
import ua.edmko.buildsrc.Coroutines
import ua.edmko.buildsrc.Database
import ua.edmko.buildsrc.LifecycleLibraries
import ua.edmko.buildsrc.Navigation
import ua.edmko.buildsrc.composeDependencies
import ua.edmko.buildsrc.coreDependencies
import ua.edmko.buildsrc.desugarJdk
import ua.edmko.buildsrc.gson
import ua.edmko.buildsrc.hiltDependencies
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Properties

plugins {
    id("dagger.hilt.android.plugin")
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
    kotlin("kapt")
    id("com.google.firebase.crashlytics")
}

val keystoreFile: File = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystoreFile))

fun getBuildTime(): String {
    val date = SimpleDateFormat("yyyy-MM-dd")
    return date.format(Date())
}

android {
    compileSdk = AndroidSdk.compile

    defaultConfig {
        applicationId = "ua.edmko.unocounter"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.compile
        versionCode = 5
        versionName = "0.1.0"
        setProperty("archivesBaseName", "unocounter${getBuildTime()}_${versionName}_$versionCode")
    }

    signingConfigs {
        create("release") {
            storeFile = file(keystoreProperties["storeFile"]!!)
            storePassword = keystoreProperties["storePassword"].toString()
            keyAlias = keystoreProperties["keyAlias"].toString()
            keyPassword = keystoreProperties["keyPassword"].toString()
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isShrinkResources = true
            isMinifyEnabled = true
            versionNameSuffix = "-R"
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ComposeLibraries.Versions.compiler
    }
    namespace = "ua.edmko.unocounter"
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
    coreDependencies()
    hiltDependencies()
    composeDependencies()
    implementation(AndroidLibraries.splash)
    implementation(LifecycleLibraries.vmKtx)
    implementation(LifecycleLibraries.runtime)
    implementation(LifecycleLibraries.vmCompose)
    implementation(gson)
    kapt(Database.compiler)
    implementation(Database.extensions)
    implementation(Database.runtime)
    implementation(Navigation.compose)
    implementation(Coroutines.android)
    implementation(Coroutines.core)
    coreLibraryDesugaring(desugarJdk)
    implementation(Analytics.analytics)
    implementation(Analytics.crashlytics)
}
