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
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

compose.desktop {
    application {
        mainClass = "NotflixDesktopKt"
    }
}
