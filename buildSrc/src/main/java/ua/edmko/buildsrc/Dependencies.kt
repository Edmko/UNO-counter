package ua.edmko.buildsrc

object AndroidSdk {
    const val min = 24
    const val compile = 30
}

object AndroidLibraries {
    object Versions {
        const val core = "1.3.2"
        const val appCompat = "1.2.0"
    }

    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
}

object HiltDependencies {

    object Versions {
        const val hilt = "2.33-beta"
        const val hiltNavigation = "1.0.0-beta01"
        const val lifecycle = "1.0.0-alpha03"
    }

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val lifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.lifecycle}"
    const val kaptHilt = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val kaptLifecycle = "androidx.hilt:hilt-compiler:${Versions.lifecycle}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-fragment:${Versions.hiltNavigation}"
}


object ComposeLibraries {
    object Versions {
        const val compose = "1.0.0-beta05"
        const val activityCompose = "1.3.0-alpha05"
        const val viewModelCompose = "1.0.0-alpha03"
    }

    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val liveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewModelCompose}"
    const val materialIconsCore =
        "androidx.compose.material:material-icons-core:${Versions.compose}"
    const val materialIconsExtended =
        "androidx.compose.material:material-icons-extended:${Versions.compose}"
}

object LifecycleLibraries {
    object Versions {
        const val lifecycle = "2.3.1"
    }

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
}