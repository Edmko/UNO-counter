package ua.edmko.buildsrc

object AndroidSdk {
    const val min = 24
    const val compile = 31
}

object Classpath {
    const val gradle = "com.android.tools.build:gradle:7.0.2"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:2.36"
    const val ktlint = "org.jlleitschuh.gradle:ktlint-gradle:10.1.0"
}

object AndroidLibraries {
    object Versions {
        const val core = "1.6.0"
        const val appCompat = "1.3.1"
        const val materialCore = "1.4.0"
    }

    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val materialCore = "com.google.android.material:material:${Versions.materialCore}"
}

object HiltDependencies {

    object Versions {
        const val hilt = "2.36"
        const val hiltNavigation = "1.0.0-alpha02"
        const val lifecycle = "1.0.0-alpha03"
        const val compilerX = "1.0.0-beta01"

    }

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val lifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.lifecycle}"
    const val kaptHilt = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val compiler = "androidx.hilt:hilt-compiler:${Versions.compilerX}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"
}

const val gson = "com.google.code.gson:gson:2.8.7"

object ComposeLibraries {
    object Versions {
        const val compose = "1.1.0-alpha06"
        const val activityCompose = "1.4.0-rc01"
        const val accompanist = "0.18.0"
        const val constraint = "1.0.0-beta02"
    }

    const val accompanist = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val iconsCore = "androidx.compose.material:material-icons-core:${Versions.compose}"
    const val iconExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val constraint =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraint}"
}

object LifecycleLibraries {
    object Versions {
        const val lifecycle = "2.4.0-beta01"
        const val compose = "2.4.0-beta01"
    }

    const val vmCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"
    const val vmKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
}

object Navigation {
    object Versions {
        const val compose = "2.4.0-alpha10"
    }

    const val compose = "androidx.navigation:navigation-compose:${Versions.compose}"
}

object Coroutines {
    object Versions {
        const val coroutines = "1.5.0"
    }

    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object Database {
    object Version {
        const val room = "2.3.0"
    }

    const val runtime = "androidx.room:room-runtime:${Version.room}"
    const val compiler = "androidx.room:room-compiler:${Version.room}"
    const val extensions = "androidx.room:room-ktx:${Version.room}"
}

const val desugarJdk = "com.android.tools:desugar_jdk_libs:1.1.5"