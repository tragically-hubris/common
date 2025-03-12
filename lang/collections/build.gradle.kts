plugins {
    id("buildsrc.convention.kotlin-jvm")
    alias(libs.plugins.ktlint)
}

dependencies {
    implementation(libs.bundles.kotlinx.ecosystem)

    testImplementation(kotlin("test"))
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotest.runner.junit5)
}
