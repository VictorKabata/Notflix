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
    implementation(project(":shared"))

    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "NotflixDesktopKt"
    }
}
