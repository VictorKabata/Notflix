plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.kotlin) apply false
    alias(libs.plugins.multiplatform) apply false
    alias(libs.plugins.jvm) apply false
    alias(libs.plugins.nativeCocoapod) apply false

    alias(libs.plugins.googleServices.plugin) apply false

    alias(libs.plugins.compose) apply false
    alias(libs.plugins.compose.compiler) apply false

    alias(libs.plugins.firebase.appDistribution.plugin) apply false
    alias(libs.plugins.firebase.crashlytics.plugin) apply false
    alias(libs.plugins.firebase.performance.plugin) apply false

    alias(libs.plugins.ktLint)
    alias(libs.plugins.detekt)
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
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
}

tasks.register("clean").configure {
    delete("build") // dependencyUpdates task, for example, writes here
}
