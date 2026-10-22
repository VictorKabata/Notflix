import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.kmp.library)
    alias(libs.plugins.kotlinX.serialization.plugin)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
    jvmToolchain(17)

    applyDefaultHierarchyTemplate()

    android {
        namespace = "com.vickbt.composeApp"
        compileSdk = 37
        minSdk = 24

        // Required so Compose Multiplatform resources are packaged into the Android library
        androidResources {
            enable = true
        }

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
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
            api(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.ui)
            implementation(libs.compose.material3)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.materialIconsExtended)
            implementation(libs.compose.material3.adaptiveNavigationSuite)

            api(libs.lifecycle.viewmodel)
            implementation(libs.lifecycle.viewmodel.compose)

            implementation(libs.coroutines)

            implementation(libs.bundles.ktor)

            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.viewmodel)

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
        }

        iosMain.dependencies {
            implementation(libs.ktor.darwin)
        }

        getByName("desktopMain").dependencies {
            implementation(libs.ktor.java)
            implementation(libs.coroutines.swing)
            implementation(compose.desktop.currentOs)
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.vickbt.composeApp.ui.windows.MainWindowKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Notflix"
            packageVersion = "1.0.0"
        }
    }
}

buildkonfig {
    packageName = "com.vickbt.shared"

    val localProperties = Properties().apply {
        val file = rootProject.file("local.properties")
        if (file.exists()) file.inputStream().use { load(it) }
    }

    defaultConfigs {
        buildConfigField(
            STRING,
            "API_KEY",
            localProperties.getProperty("api_key") ?: ""
        )
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspAndroid", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
    add("kspDesktop", libs.room.compiler)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.vickbt.shared.resources"
    generateResClass = always
}
