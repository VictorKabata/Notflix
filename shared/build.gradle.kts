import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.nativeCocoapod)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinX.serialization.plugin)
    alias(libs.plugins.kmp.nativeCoroutines.plugin)
    alias(libs.plugins.buildKonfig)
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
    }
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }
    iosTarget("iOS") {}

    cocoapods {
        version = "1.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        // podfile = project.file("../appiOS/Podfile")

        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        sourceSets["commonMain"].dependencies {
            // Compose multiplatform
            /*implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)*/

            implementation(libs.kotlinX.coroutines)

            api(libs.koin.core)

            api(libs.ktor.core)
            api(libs.ktor.cio)
            implementation(libs.ktor.contentNegotiation)
            implementation(libs.ktor.json)
            implementation(libs.ktor.logging)

            implementation(libs.kotlinX.serializationJson)

            implementation(libs.multiplatformSettings.noArg)
            implementation(libs.multiplatformSettings.coroutines)

            api(libs.napier)

            implementation(libs.kotlinX.dateTime)
        }

        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test"))
            implementation(libs.turbine)
            implementation(libs.ktor.mock)
            implementation(libs.kotlinX.testResources)
            implementation(libs.kotlinX.coroutines.test)
            implementation(libs.multiplatformSettings.test)
        }

        sourceSets["androidMain"].dependencies {
        }

        sourceSets["androidTest"].dependencies {}

        sourceSets["iOSMain"].dependencies {
        }

        sourceSets["iOSTest"].dependencies {}

        sourceSets["jvmMain"].dependencies {
        }

        sourceSets["jvmTest"].dependencies {}
    }
}

buildkonfig {
    packageName = "com.vickikbt.shared"

    defaultConfigs {
        buildConfigField(
            STRING,
            "API_KEY",
            gradleLocalProperties(rootDir).getProperty("api_key") ?: ""
        )
    }
}

/**
 * Avoid error: Consumable configurations with identical capabilities within a project (other than the default configuration)
 * must have unique attributes, but configuration ':shared:podReleaseFrameworkIosFat' and [configuration ':shared:podReleaseFrameworkIOS']
 * contain identical attribute sets. Consider adding an additional attribute to one of the configurations to disambiguate them.
 * Run the 'outgoingVariants' task for more details. See https://docs.gradle.org/8.1.1/userguide/upgrading_version_7.html#unique_attribute_sets
 * for more details.
 */
/*configurations {
    named("podDebugFrameworkIosFat") {
        attributes {
            attribute(Attribute.of("org.gradle.fatness", String::class.java), "fat")
        }
    }
    named("podReleaseFrameworkIosFat") {
        attributes {
            attribute(Attribute.of("org.gradle.fatness", String::class.java), "fat")
        }
    }
}*/
