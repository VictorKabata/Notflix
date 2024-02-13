import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.nativeCocoapod)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinX.serialization.plugin)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.compose)

    alias(libs.plugins.sqlDelight)

    // id("com.google.devtools.ksp")
}

@OptIn(
    ExperimentalKotlinGradlePluginApi::class,
    ExperimentalComposeLibrary::class
)
kotlin {
    kotlin.applyDefaultHierarchyTemplate()

    androidTarget {
        instrumentedTestVariant {
            sourceSetTree.set(KotlinSourceSetTree.test)

            dependencies {
                implementation("androidx.compose.ui:ui-test-junit4-android:1.5.4")
                debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.4")
            }
        }

    }

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        when {
            System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
            System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
            else -> ::iosX64
        }
    iosTarget("ios") {}

    jvm()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "github.com/VictorKabata/Notflix"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../appiOS/Podfile")
        framework {
            baseName = "shared"
            isStatic = false
        }
    }

    sourceSets {
        sourceSets["commonMain"].dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.components.resources)
            api(compose.materialIconsExtended)

            implementation(libs.kotlinX.coroutines)

            api(libs.ktor.core)
            implementation(libs.ktor.contentNegotiation)
            implementation(libs.ktor.json)
            implementation(libs.ktor.logging)

            api(libs.koin.core)
            implementation(libs.koin.compose)

            implementation(libs.kotlinX.serializationJson)

            implementation(libs.kotlinX.dateTime)

            implementation(libs.multiplatformSettings.noArg)
            implementation(libs.multiplatformSettings.coroutines)

            api(libs.napier)

            implementation(libs.imageLoader)

            api(libs.preCompose)
            api(libs.preCompose.viewmodel)

            implementation(libs.sqlDelight.coroutine)

            // implementation(libs.material.windowSizeClass)
        }

        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test"))

            implementation(libs.kotlinX.coroutines.test)

            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)

            // ktor
            implementation(libs.ktor.mock)

            implementation("io.mockative:mockative:2.0.1")

        }

        sourceSets["androidMain"].dependencies {
            implementation(libs.ktor.android)
            implementation(libs.sqlDelight.android)
        }

        // sourceSets["androidUnitTest"].dependencies {}

        sourceSets["iosMain"].dependencies {
            implementation(libs.ktor.darwin)
            implementation(libs.sqlDelight.native)
        }

        sourceSets["iosTest"].dependencies {}

        sourceSets["jvmMain"].dependencies {
            implementation(libs.sqlDelight.jvm)
        }

        sourceSets["jvmTest"].dependencies {}
    }
}

dependencies {
    configurations
        .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
        .forEach {
            add(it.name, "io.mockative:mockative-processor:2.0.1")
        }
}

android {
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    namespace = "com.vickbt.shared"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
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
