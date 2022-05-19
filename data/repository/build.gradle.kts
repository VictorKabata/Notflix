plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
}

android {
    compileSdkVersion(AndroidSDK.compileSdkVersion)
    buildToolsVersion(AndroidSDK.buildToolVersion)

    defaultConfig {
        minSdkVersion(AndroidSDK.minSdkVersion)
        targetSdkVersion(AndroidSDK.targetSdkVersion)

        testInstrumentationRunner = AndroidSDK.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    api(project(BuildModules.Cache))

    implementation(project(BuildModules.Shared))

    implementation(AndroidDependencies.kotlin)
    implementation(AndroidDependencies.androidCore)

    implementation(AndroidDependencies.koinAndroid)

    // Paging 3
    implementation(AndroidDependencies.paging)
}
