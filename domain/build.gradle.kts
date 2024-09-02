plugins {
    alias(libs.plugins.jetbrainsKotlinJvm)
}

dependencies {
    implementation(libs.jsr250.api)
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)
}
