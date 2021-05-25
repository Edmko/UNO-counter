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
        classpath("com.android.tools.build:gradle:7.0.0-beta02")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.35.1")

    }
}