package ua.edmko.build_logic

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class CommonComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            applyPlugins(target.pluginManager)
            androidConfig()
            dependenciesConfig()
        }
    }

    private fun applyPlugins(manager: PluginManager) {
        with(manager) {
            apply("org.jetbrains.kotlin.plugin.compose")
        }
    }

    private fun Project.androidConfig() {
        extensions.getByType<BaseExtension>().apply {
            buildFeatures.compose = true
            composeOptions {
                kotlinCompilerExtensionVersion = libs.findVersion("kotlinCompilerExtension").get().toString()
            }
        }
    }

    private fun Project.dependenciesConfig() {
        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))
        }
    }
}
