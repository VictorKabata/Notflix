import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(BuildPlugins.multiplatform)
    id(BuildPlugins.androidLibrary)
    kotlin(BuildPlugins.kotlinXSerialization) version Versions.kotlinSerialization
    id(BuildPlugins.nativeCoroutines)
}

android {
    compileSdk = AndroidSDK.targetSdkVersion
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = AndroidSDK.minSdkVersion
        targetSdk = AndroidSDK.targetSdkVersion
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
            implementation(KmmDependencies.kotlinxCoroutines)

            implementation(KmmDependencies.kotlinxSerialization)
            implementation(KmmDependencies.kotlinxDateTime)

            api(KmmDependencies.koinCore)

            implementation(KmmDependencies.ktorCore)
            implementation(KmmDependencies.ktorSerialization)
            implementation(KmmDependencies.ktorLogging)

            api(KmmDependencies.napier)

            implementation(KmmDependencies.multiplatformSettings)
            implementation(KmmDependencies.multiplatformSettingsCoroutines)
        }

        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test"))
            // implementation(KmmDependencies.mockk)
        }

        sourceSets["androidMain"].dependencies {
            implementation(KmmDependencies.ktorAndroid)
        }

        sourceSets["androidTest"].dependencies {}

        sourceSets["jvmMain"].dependencies {
            api(KmmDependencies.ktorJvm)
        }

        sourceSets["iOSMain"].dependencies {
            implementation(KmmDependencies.ktoriOS)
        }

        sourceSets["iOSTest"].dependencies {}
    }
}
