plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.kotlin) apply false
    alias(libs.plugins.multiplatform) apply false
    alias(libs.plugins.jvm) apply false
    alias(libs.plugins.nativeCocoapod) apply false

    alias(libs.plugins.googleServices.plugin) apply false

    alias(libs.plugins.compose) apply false

    alias(libs.plugins.firebase.appDistribution.plugin) apply false
    alias(libs.plugins.firebase.crashlytics.plugin) apply false
    alias(libs.plugins.firebase.performance.plugin) apply false

    // alias(libs.plugins.ktLint)
    alias(libs.plugins.detekt)
    alias(libs.plugins.gradleVersionUpdates)
    // alias(libs.plugins.kotlinX.testResources)
}

allprojects {
    /*repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }*/

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
            // allWarningsAsErrors = true
            freeCompilerArgs = listOf(
                "-opt-in=kotlin.RequiresOptIn",
            )
        }
    }

    //ToDo: Add ktLint

}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        parallel = true
        config = files("${project.rootDir}/config/detekt/detekt.yml")
    }

    // apply(plugin = "com.goncalossilva.resources")

    tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
        checkForGradleUpdate = true
        outputDir = "build/dependencyUpdates"
        reportfileName = "report"
    }
}

tasks.register("clean").configure {
    delete("build") // dependencyUpdates task, for example, writes here
}
