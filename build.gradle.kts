// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    allprojects {
        repositories {
            google()
            jcenter()
            mavenCentral()
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha14")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.33-beta")

    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}