buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(ua.edmko.buildsrc.Classpath.gradle)
        classpath(ua.edmko.buildsrc.Classpath.kotlin)
        classpath(ua.edmko.buildsrc.Classpath.hilt)
        classpath(ua.edmko.buildsrc.Classpath.ktlint)
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