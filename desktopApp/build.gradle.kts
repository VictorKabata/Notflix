import org.jetbrains.compose.compose

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose") version Versions.composeDesktop
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(project(BuildModules.shared))

    implementation(compose.desktop.currentOs)
    // implementation(DesktopDependencies.kamel)
}

compose.desktop {
    application {
        mainClass = "NotflixDesktopKt"
    }

    /*nativeDistributions {
        targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
    }*/
}
