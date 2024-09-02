import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Properties

plugins {
    alias(libs.plugins.plugin.application)
    alias(libs.plugins.plugin.compose)
    alias(libs.plugins.plugin.hilt)
}

val keystoreFile: File = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystoreFile))

fun getBuildTime(): String {
    val date = SimpleDateFormat("yyyy-MM-dd")
    return date.format(Date())
}

android {
    namespace = "ua.edmko.unocounter"
    defaultConfig {
        applicationId = "ua.edmko.unocounter"
        versionCode = 6
        versionName = "0.1.1"
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    ksp(libs.hilt.android.compiler)
    kspTest(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.splash)
    implementation(libs.gson)
}
