package ua.edmko.buildsrc

object AndroidSdk {
    const val min = 24
    const val compile = 30
}

object AndroidLibraries {
    object Versions {
        const val core = "1.3.2"
        const val appCompat = "1.2.0"
        const val materialCore = "1.3.0"
    }

    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val materialCore = "com.google.android.material:material:${Versions.materialCore}"
}

object HiltDependencies {

    object Versions {
        const val hilt = "2.35.1"
        const val hiltNavigation = "1.0.0-alpha01"
        const val lifecycle = "1.0.0-alpha03"
        const val compilerX = "1.0.0-beta01"
    }

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val lifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.lifecycle}"
    const val kaptHilt = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val compiler = "androidx.hilt:hilt-compiler:${Versions.compilerX}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"
}


object ComposeLibraries {
    object Versions {
        const val compose = "1.0.0-beta06"
        const val activityCompose = "1.3.0-alpha07"
        const val viewModelCompose = "1.0.0-alpha04"
    }

    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewModelCompose}"
    const val materialIconsCore =
        "androidx.compose.material:material-icons-core:${Versions.compose}"
    const val materialIconsExtended =
        "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val constraint = "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha06"
}

object LifecycleLibraries {
    object Versions {
        const val lifecycle = "2.3.1"
    }
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
}

object Navigation {
    object Versions {
        const val compose = "1.0.0-alpha10"
    }

    const val compose = "androidx.navigation:navigation-compose:${Versions.compose}"
}

object Coroutines {
    object Versions {
        const val coroutines = "1.5.0-RC"
    }

    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object Database {
    object Version{
        const val room = "2.3.0"
    }
    const val runtime = "androidx.room:room-runtime:${Version.room}"
    const val compiler = "androidx.room:room-compiler:${Version.room}"
    const val extensions = "androidx.room:room-ktx:${Version.room}"
}