import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

group = "ua.edmko.mealmanager.buildlogic"

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

gradlePlugin {
    plugins {
        register("composeConfig") {
            id = "plugin.compose.config"
            implementationClass = "ua.edmko.build_logic.CommonComposePlugin"
        }
        register("hiltConfig") {
            id = "plugin.hilt.config"
            implementationClass = "ua.edmko.build_logic.HiltPlugin"
        }
        register("androidLibraryConfig") {
            id = "plugin.android.library.config"
            implementationClass = "ua.edmko.build_logic.AndroidLibraryPlugin"
        }
        register("applicationConfig") {
            id = "plugin.application.config"
            implementationClass = "ua.edmko.build_logic.ApplicationPlugin"
        }
        register("composeFeaturePlugin"){
            id = "plugin.feature.compose.config"
            implementationClass = "ua.edmko.build_logic.ComposeFeaturePlugin"
        }
    }
}

dependencies {
    compileOnly(libs.gradle)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.hilt.android.compiler)
    compileOnly(libs.room.gradlePlugin)
}