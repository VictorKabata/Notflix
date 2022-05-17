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
    implementation(project(BuildModules.Shared))

    implementation(compose.desktop.currentOs)

    // implementation(Libraries.ratingBar)
}

compose.desktop {
    application {
        mainClass = "NotflixDesktopKt"
    }
}
