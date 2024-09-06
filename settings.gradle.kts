pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:testClasses"))
rootProject.name = "UnoCounter"
include(":app")
include(":domain")
include(":data")
include(":navigation")
include(":core")
include(":features:setup")
include(":features:endgame")
include(":features:game")
include(":features:players")
include(":core-ui")
include(":features:privacy")
include(":features:settings")
