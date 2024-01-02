import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import dev.icerock.gradle.MRVisibility
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.nativeCocoapod)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinX.serialization.plugin)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.compose)

    alias(libs.plugins.sqlDelight)

    id("dev.icerock.mobile.multiplatform-resources")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    kotlin.applyDefaultHierarchyTemplate()

    androidTarget()

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
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class) api(compose.components.resources)
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

            implementation(libs.moko.resource)
            implementation(libs.moko.compose)

            // implementation(libs.material.windowSizeClass)
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

        /**Temporary fix for Moko resources bug @see[moko resource issue](https://github.com/icerockdev/moko-resources/issues/618)
         *It should be removed once MR supports kotlin v1.9.10 and compose v1.5.0 @see[link](https://github.com/icerockdev/moko-resources/pull/575)*/
        getByName("androidMain").dependsOn(commonMain.get())
        getByName("jvmMain").dependsOn(commonMain.get())
        getByName("iosMain").dependsOn(commonMain.get())
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
            STRING, "API_KEY", gradleLocalProperties(rootDir).getProperty("api_key") ?: ""
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

multiplatformResources {
    multiplatformResourcesPackage = "com.vickbt.notflix"
    multiplatformResourcesVisibility = MRVisibility.Internal
}
