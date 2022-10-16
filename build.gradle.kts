buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(Plugins.kotlin)
        classpath(Plugins.gradle)
        classpath(Plugins.kmpNativeCoroutines)
        classpath(Plugins.googleServices)
        classpath(Plugins.firebaseAppDistribution)
        classpath(Plugins.firebaseCrashlytics)
        classpath(Plugins.firebasePerformance)
    }
}

plugins {
    id(Plugins.ktLint) version Versions.ktLint
    id(Plugins.detekt) version (Versions.detekt)
    id(Plugins.kotlinxTestResource) version (Versions.kotlinxTestResources)
    id(Plugins.gradleVersionUpdates) version (Versions.gradleVersionUpdate)
}

allprojects {

    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

subprojects {
    apply(plugin = Plugins.ktLint)
    ktlint {
        debug.set(true)
        verbose.set(true)
        android.set(false)
        outputToConsole.set(true)
        outputColorName.set("RED")
        filter {
            enableExperimentalRules.set(true)
            exclude { projectDir.toURI().relativize(it.file.toURI()).path.contains("/generated/") }
            // include { projectDir.toURI().relativize(it.file.toURI()).path.contains("/kotlin/") }
            include("**/kotlin/**")
        }
    }

    apply(plugin = Plugins.detekt)
    detekt {
        parallel = true
        config = files("${project.rootDir}/config/detekt/detekt.yml")
    }

    apply(plugin = Plugins.kotlinxTestResource)

    tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
        checkForGradleUpdate = true
        outputDir = "build/dependencyUpdates"
        reportfileName = "report"
    }
}

tasks.register("clean").configure {
    delete("build")
}
