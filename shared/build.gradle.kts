import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.nativeCocoapod)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinX.serialization.plugin)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.compose)
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
    }
    namespace = "com.vickikbt.shared"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }

        sourceSets["commonMain"].resources.srcDirs("src/commonMain/resources")
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../appiOS/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }

        extraSpecAttributes["resources"] =
            "['src/commonMain/resources/**', 'src/iosMain/resources/**']"

    }

    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            implementation(libs.kotlinX.atomicfu) // ToDo: Remove later

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
        }

        /*sourceSets["commonTest"].dependencies {
            implementation(kotlin("test"))
            implementation(libs.turbine)
            implementation(libs.ktor.mock)
            implementation(libs.kotlinX.coroutines.test)
            implementation(libs.multiplatformSettings.test)
        }*/

        sourceSets["androidMain"].dependencies {
            implementation(libs.ktor.android)
        }

        // sourceSets["androidTest"].dependencies {}

        sourceSets["iosMain"].dependencies {
            implementation(libs.ktor.darwin)
        }

        sourceSets["iosTest"].dependencies {}

        sourceSets["jvmMain"].dependencies {
            implementation(libs.ktor.java)
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
