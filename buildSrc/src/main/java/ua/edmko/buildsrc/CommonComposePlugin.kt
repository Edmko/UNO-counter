package ua.edmko.buildsrc

import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies

class CommonComposePlugin : Plugin<Project> {

    private val Project.android
        get() = this.extensions.getByType(BaseExtension::class.java)

    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            androidConfig()
            dependeciesConfig()
        }
    }

    private fun Project.applyPlugins() {
        plugins.run {
            apply("com.android.library")
            apply("kotlin-android")
            apply("kotlin-kapt")
        }
    }

    private fun Project.androidConfig() {
        android.apply {
            setCompileSdkVersion(AndroidSdk.compile)
            defaultConfig {
                minSdk = AndroidSdk.min
            }
            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                }
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            buildFeatures.compose = true
            composeOptions {
                kotlinCompilerExtensionVersion = ComposeLibraries.Versions.compose
            }
        }
    }

    private fun Project.dependeciesConfig() {
        dependencies {
            composeDependencies()
            hiltDependencies()
            coreDependencies()
        }
    }
}