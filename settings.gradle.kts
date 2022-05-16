include(":androidApp")
include(":shared")
include(":desktopApp")

include(":data")
include(":data:cache")

rootProject.name = "Notflix"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

/*include(BuildModules.App)
include(BuildModules.Domain)
include(BuildModules.Repository)
include(BuildModules.Network)
include(BuildModules.Cache)
rootProject.name = "Notflix"*/
