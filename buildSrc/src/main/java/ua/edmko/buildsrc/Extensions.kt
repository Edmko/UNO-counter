package ua.edmko.buildsrc

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.composeDependencies() {
    "implementation"(ua.edmko.buildsrc.ComposeLibraries.ui)
    "implementation"(ua.edmko.buildsrc.ComposeLibraries.uiTooling)
    "implementation"(ua.edmko.buildsrc.ComposeLibraries.foundation)
    "implementation"(ua.edmko.buildsrc.ComposeLibraries.foundationLayout)
    "implementation"(ua.edmko.buildsrc.ComposeLibraries.material)
    "implementation"(ua.edmko.buildsrc.ComposeLibraries.iconsCore)
    "implementation"(ua.edmko.buildsrc.ComposeLibraries.iconExtended)
    "implementation"(ua.edmko.buildsrc.ComposeLibraries.constraint)
    "implementation"(ua.edmko.buildsrc.ComposeLibraries.systemUiController)
}

fun DependencyHandlerScope.coreDependencies(){
    "implementation"(ua.edmko.buildsrc.AndroidLibraries.core)
    "implementation"(ua.edmko.buildsrc.AndroidLibraries.appCompat)
    "implementation"(ua.edmko.buildsrc.AndroidLibraries.materialCore)
}

fun DependencyHandlerScope.hiltDependencies(){
    "kapt"(ua.edmko.buildsrc.HiltDependencies.kaptHilt)
    "implementation"(ua.edmko.buildsrc.HiltDependencies.hilt)
    "implementation"(ua.edmko.buildsrc.HiltDependencies.hiltNavigation)
}