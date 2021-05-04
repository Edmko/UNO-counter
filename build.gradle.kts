// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    allprojects {
        repositories {
            google()
            mavenCentral()
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha15")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.35.1")

    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}