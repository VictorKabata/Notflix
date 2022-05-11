buildscript {

    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(ProjectLevelPlugins.gradle)
        classpath(ProjectLevelPlugins.kotlin)
        classpath(ProjectLevelPlugins.firebaseCrashlyitics)
        classpath(ProjectLevelPlugins.googleServices)
        classpath(ProjectLevelPlugins.ktLint)
    }
}

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

allprojects {

    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }

    apply(plugin = BuildPlugins.ktlintPlugin)
    ktlint {
        debug.set(true)
        verbose.set(true)
        android.set(false)
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }
}

tasks.register("clean").configure {
    delete("build")
}
