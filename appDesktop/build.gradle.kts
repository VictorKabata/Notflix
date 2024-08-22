plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

dependencies {
    implementation(project(":shared"))

    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "NotflixApplicationKt"
    }
}
