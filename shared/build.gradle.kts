import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(Plugins.multiplatform)
    kotlin(Plugins.nativeCocoaPods)
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinXSerialization) version Versions.kotlinSerialization
    id(Plugins.nativeCoroutines)
    id(Plugins.sqlDelight) version Versions.sqlDelight
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
        //version = "1"

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

            implementation(MultiplatformDependencies.kotlinxSerialization)

            implementation(MultiplatformDependencies.kotlinxDateTime)

            api(MultiplatformDependencies.koinCore)

            api(MultiplatformDependencies.napier)

            implementation(MultiplatformDependencies.ktorCore)
            implementation(MultiplatformDependencies.ktorSerialization)
            implementation(MultiplatformDependencies.ktorLogging)

            implementation(MultiplatformDependencies.sqlDelight)
            implementation(MultiplatformDependencies.sqlDelightCoroutine)

            implementation(MultiplatformDependencies.multiplatformSettings)
            implementation(MultiplatformDependencies.multiplatformSettingsCoroutines)
        }

        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test"))
            implementation(MultiplatformDependencies.ktorMock)
            implementation(MultiplatformDependencies.kotlinxTestResources)
            implementation(MultiplatformDependencies.kotlinxCoroutinesTest)
            implementation(MultiplatformDependencies.multiplatformSettingsTest)
        }

        sourceSets["androidMain"].dependencies {
            implementation(MultiplatformDependencies.ktorAndroid)
            implementation(MultiplatformDependencies.sqlDelightAndroid)
        }

        sourceSets["androidTest"].dependencies {}

        sourceSets["iOSMain"].dependencies {
            implementation(MultiplatformDependencies.ktoriOS)
            implementation(MultiplatformDependencies.sqlDelightNative)
        }

        sourceSets["iOSTest"].dependencies {}

        sourceSets["jvmMain"].dependencies {
            api(MultiplatformDependencies.ktorJvm)
            implementation(MultiplatformDependencies.sqlDelightJVM)
        }

        sourceSets["jvmTest"].dependencies {}
    }
}

sqldelight {
    database(name = "AppDatabase") {
        packageName = "com.vickikbt.shared.data.cache.sqldelight"
        sourceFolders = listOf("kotlin")
    }
}
