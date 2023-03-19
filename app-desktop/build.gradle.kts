plugins {
    id(libs.plugins.jvm.get().pluginId)
    alias(libs.plugins.compose.desktop.plugin)
}

dependencies {
    implementation(project(BuildModules.shared))

    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "NotflixDesktopKt"
    }

    /*nativeDistributions {
        targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
    }*/
}
