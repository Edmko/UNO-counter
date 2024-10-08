package ua.edmko.build_logic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }
            dependencies {
                "implementation"(libs.findLibrary("hilt-android").get())
                "ksp"(libs.findLibrary("hilt-android-compiler").get())
                "testImplementation"(libs.findLibrary("hilt_test").get())
                "kspTest"(libs.findLibrary("hilt-android-compiler").get())
            }
        }
    }
}
