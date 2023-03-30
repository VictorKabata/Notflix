plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.compose.desktop.plugin)
}

dependencies {
    implementation(project(":shared"))

    implementation(compose.desktop.currentOs)

    implementation(libs.voyager.core)
    implementation(libs.voyager.navigator)
    // implementation(libs.voyager.koin)
}

compose.desktop {
    application {
        mainClass = "NotflixDesktopKt"
    }

    /*nativeDistributions {
        targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
    }*/
}
