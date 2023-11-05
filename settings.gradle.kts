rootProject.name = "Notflix"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
        maven(url = "https://androidx.dev/storage/compose-compiler/repository")
    }
}

include(":appAndroid")
include(":appDesktop")
include(":shared")
