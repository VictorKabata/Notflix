import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(Plugins.multiplatform)
    kotlin(Plugins.nativeCocoaPods)
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinXSerialization) version Versions.kotlin
    id(Plugins.nativeCoroutines)
    id(Plugins.sqlDelight) version Versions.sqlDelight
    id(Plugins.kover) version Versions.kover
}

android {
    compileSdk = AndroidSdk.compileSdkVersion
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion
    }
}

kotlin {
    android()

    jvm()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }
    iosTarget("iOS") {}
    version = "1"
    cocoapods {
        // version = "1"

        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        // ios.deploymentTarget = "14.1"
        podfile = project.file("../iOSNotflix/Podfile")

        framework {
            baseName = "shared"
            isStatic = false
        }
    }

    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(MultiplatformDependencies.kotlinxCoroutines)

            api(MultiplatformDependencies.koinCore)

            api(MultiplatformDependencies.ktorCore)
            api(MultiplatformDependencies.ktorCio)
            implementation(MultiplatformDependencies.ktorContentNegotiation)
            implementation(MultiplatformDependencies.ktorJson)
            implementation(MultiplatformDependencies.ktorLogging)

            implementation(MultiplatformDependencies.kotlinxSerializationJson)

            implementation(MultiplatformDependencies.multiplatformSettings)
            implementation(MultiplatformDependencies.multiplatformSettingsCoroutines)

            api(MultiplatformDependencies.napier)

            implementation(MultiplatformDependencies.kotlinxDateTime)
        }

        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test"))
            implementation(MultiplatformDependencies.ktorMock)
            implementation(MultiplatformDependencies.kotlinxTestResources)
            implementation(MultiplatformDependencies.kotlinxCoroutinesTest)
            implementation(MultiplatformDependencies.multiplatformSettingsTest)
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

kover {
    verify {
        rule {
            name = "Minimal line coverage rate in percents"
            bound { minValue = 50 }
        }
    }
}
