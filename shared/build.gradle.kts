import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.kotlinX.serialization.plugin)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.sqlDelight)

    alias(libs.plugins.android.application)
    alias(libs.plugins.googleServices.plugin)
    alias(libs.plugins.firebase.appDistribution.plugin)
    alias(libs.plugins.firebase.crashlytics.plugin)
    alias(libs.plugins.firebase.performance.plugin)

}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    kotlin.applyDefaultHierarchyTemplate()

    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = false
        }
    }

    jvm("desktop")

    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.components.resources)
            api(compose.materialIconsExtended)

            implementation(libs.coroutines)

            implementation(libs.bundles.ktor)

            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.composeViewModel)

            implementation(libs.kotlinX.serializationJson)

            implementation(libs.kotlinX.dateTime)

            api(libs.napier)

            implementation(libs.bundles.coil)

            implementation(libs.navigation)

            implementation(libs.sqlDelight.coroutine)

            implementation(libs.datastore.preferences)

            // implementation(libs.material.windowSizeClass)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.turbine)
            implementation(libs.ktor.mock)
            implementation(libs.kotlinX.coroutines.test)
        }

        androidMain.dependencies {
            implementation(libs.ktor.android)
            implementation(libs.sqlDelight.android)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.androidX.activity)

            // Firebase
            implementation(libs.firebase.analytics)
            implementation(libs.firebase.crashlytics)
            implementation(libs.firebase.performance)
        }

        iosMain.dependencies {
            implementation(libs.ktor.darwin)
            implementation(libs.sqlDelight.native)
        }

        sourceSets["desktopMain"].dependencies {
            implementation(libs.ktor.java)
            implementation(libs.sqlDelight.jvm)
            implementation(libs.coroutines.swing)
            implementation(compose.desktop.currentOs)
        }

        sourceSets["desktopTest"].dependencies {}
    }
}

android {
    compileSdk = 34
    defaultConfig {
        applicationId = "com.vickbt.notflix"

        minSdk = 24
        targetSdk = compileSdk
        versionCode = if (System.getenv("VERSION_CODE").isNullOrEmpty()) {
            1
        } else {
            System.getenv("VERSION_CODE").toInt()
        }
        versionName = if (System.getenv("VERSION_NAME").isNullOrEmpty()) {
            "1.0.0"
        } else {
            System.getenv("VERSION_NAME")?.toString()
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    namespace = "com.vickbt.notflix"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    dependencies {
        debugImplementation(libs.leakCanary)
        implementation(platform(libs.firebase.bom))
    }
}

compose.desktop {
    application {
        mainClass = "com.vickbt.notflix.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Notflix"
            packageVersion = "1.0.0"
        }
    }
}

buildkonfig {
    packageName = "com.vickbt.shared"

    defaultConfigs {
        buildConfigField(
            STRING,
            "API_KEY",
            gradleLocalProperties(rootDir).getProperty("api_key") ?: ""
        )
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.vickbt.shared.data.cache.sqldelight")
            srcDirs.setFrom("src/commonMain/kotlin")
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.vickbt.shared.resources"
    generateResClass = always
}
