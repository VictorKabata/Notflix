plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.kotlin) apply false
    alias(libs.plugins.multiplatform) apply false

    alias(libs.plugins.ktLint)
    alias(libs.plugins.detekt)
    alias(libs.plugins.gradleVersionUpdates)
    alias(libs.plugins.kotlinX.testResources)
}

buildscript {
    dependencies {
        // classpath(libs.kotlin)
        // classpath(libs.gradle)
        classpath(libs.googleServices)
        classpath(libs.firebase.appDistribution.plugin)
        classpath(libs.firebase.crashlytics.plugin)
        classpath(libs.firebase.performance.plugin)
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        debug.set(true)
        verbose.set(true)
        android.set(false)
        outputToConsole.set(true)
        outputColorName.set("RED")
        filter {
            enableExperimentalRules.set(true)
            exclude { projectDir.toURI().relativize(it.file.toURI()).path.contains("/generated/") }
            include("**/kotlin/**")
        }
    }

    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        parallel = true
        config = files("${project.rootDir}/config/detekt/detekt.yml")
    }

    apply(plugin = "com.goncalossilva.resources")

    tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
        checkForGradleUpdate = true
        outputDir = "build/dependencyUpdates"
        reportfileName = "report"
    }
}

tasks.register("clean").configure {
    delete("build")
}
