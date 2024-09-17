rootProject.name = "Notflix"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
}

include(":appAndroid")
include(":appDesktop")
include(":appWeb")
include(":shared")
