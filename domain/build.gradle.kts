plugins {
    id("kotlin")
}

dependencies {
    implementation(ua.edmko.buildsrc.Coroutines.core)
    implementation("javax.annotation:jsr250-api:1.0")
    implementation("javax.inject:javax.inject:1")
}