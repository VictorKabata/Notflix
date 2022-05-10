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
    api(project(BuildModules.Domain))
    api(project(BuildModules.Network))

    implementation(Libraries.kotlin)
    implementation(Libraries.androidCore)

    // Room
    api(Libraries.roomRuntime)
    kapt(Libraries.roomCompiler)
    api(Libraries.roomKtx)

    // DataStore
    implementation(Libraries.datastore)

    implementation(Libraries.koinAndroid)

    implementation(Libraries.gsonConvertor)

    // Paging 3
    implementation(Libraries.paging)
}
