import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.kotlinX.serialization.plugin)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.compose)

    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.android.application)
    alias(libs.plugins.googleServices.plugin)
    alias(libs.plugins.firebase.appDistribution.plugin)
    alias(libs.plugins.firebase.crashlytics.plugin)
    alias(libs.plugins.firebase.performance.plugin)
}

kotlin {
    jvmToolchain(17)
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
            isStatic = true
        }
    }

    jvm("desktop")

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(compose.materialIconsExtended)
            implementation(compose.material3AdaptiveNavigationSuite)

            implementation(libs.coroutines)

            implementation(libs.bundles.ktor)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.composeViewModel)

            implementation(libs.kotlinX.serializationJson)

            implementation(libs.kotlinX.dateTime)

            implementation(libs.napier)

            implementation(libs.bundles.coil)

            implementation(libs.navigation)

            implementation(libs.datastore.preferences)

            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            implementation(libs.bundles.paging)

            implementation(libs.bundles.kmpPalette)

            implementation(libs.adaptive.core)

            implementation(libs.sdpSsp)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.turbine)
            implementation(libs.ktor.mock)
            implementation(libs.kotlinX.coroutines.test)
        }

        androidMain.dependencies {
            implementation(libs.ktor.android)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.androidX.activity)

            implementation(libs.firebase.analytics)
            implementation(libs.firebase.crashlytics)
            implementation(libs.firebase.performance)
        }

        iosMain.dependencies {
            implementation(libs.ktor.darwin)
        }

        sourceSets["desktopMain"].dependencies {
            implementation(libs.ktor.java)
            implementation(libs.coroutines.swing)
            implementation(compose.desktop.currentOs)
        }

        sourceSets["desktopTest"].dependencies {}
    }
}

android {
    compileSdk = 35
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

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspAndroid", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
    add("kspDesktop", libs.room.compiler)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.vickbt.shared.resources"
    generateResClass = always
}
