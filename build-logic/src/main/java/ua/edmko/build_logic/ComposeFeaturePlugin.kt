package ua.edmko.build_logic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.dependencies

class ComposeFeaturePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        applyPlugins(target.pluginManager)
        target.addDependencies()
    }

    private fun applyPlugins(manager: PluginManager) {
        with(manager) {
            apply("plugin.android.library.config")
            apply("plugin.hilt.config")
            apply("plugin.compose.config")
        }
    }

    private fun Project.addDependencies(){
        dependencies {
            add("implementation", project(":domain"))
            add("implementation", project(":core-ui"))
            add("implementation", project(":core"))
            add("implementation", libs.findLibrary("navigation.compose").get())
            add("implementation", libs.findLibrary("lifecycle.viewmodel.compose").get())
            add("implementation", libs.findLibrary("androidx.navigation.runtime.ktx").get())
            add("implementation", libs.findLibrary("hilt-navigation-compose").get())
            add("implementation", libs.findLibrary("androidx-compose-ui_tooling").get())
        }
    }
}
