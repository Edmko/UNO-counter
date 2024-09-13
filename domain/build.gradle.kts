plugins {
    alias(libs.plugins.jetbrainsKotlinJvm)
}

dependencies {
    implementation(libs.jsr250.api)
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)
}
