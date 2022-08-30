import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(Plugins.multiplatform)
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinXSerialization) version Versions.kotlinSerialization
    id(Plugins.nativeCoroutines)
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

    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(MultiplatformDependencies.kotlinxCoroutines)

            implementation(MultiplatformDependencies.kotlinxSerialization)
            implementation(MultiplatformDependencies.kotlinxDateTime)

            api(MultiplatformDependencies.koinCore)

            implementation(MultiplatformDependencies.ktorCore)
            implementation(MultiplatformDependencies.ktorSerialization)
            implementation(MultiplatformDependencies.ktorLogging)

            api(MultiplatformDependencies.napier)

            implementation(MultiplatformDependencies.multiplatformSettings)
            implementation(MultiplatformDependencies.multiplatformSettingsCoroutines)
        }

        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test"))
            implementation(MultiplatformDependencies.ktorMock)
            implementation(MultiplatformDependencies.kotlinxTestResources)
        }

        sourceSets["androidMain"].dependencies {
            implementation(MultiplatformDependencies.ktorAndroid)
        }

        sourceSets["androidTest"].dependencies {}

        sourceSets["jvmMain"].dependencies {
            api(MultiplatformDependencies.ktorJvm)
        }

        sourceSets["iOSMain"].dependencies {
            implementation(MultiplatformDependencies.ktoriOS)
        }

        sourceSets["iOSTest"].dependencies {}
    }
}
