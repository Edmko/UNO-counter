buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(ua.edmko.buildsrc.Classpath.gradle)
        classpath(kotlin("gradle-plugin", version = ua.edmko.buildsrc.Classpath.Versions.kotlin))
        classpath(ua.edmko.buildsrc.Classpath.hilt)
        classpath(ua.edmko.buildsrc.Classpath.ktlint)
        classpath(ua.edmko.buildsrc.Analytics.googleServices)
        classpath(ua.edmko.buildsrc.Analytics.crashlyticGradle)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}