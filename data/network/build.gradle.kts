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

    implementation(Libraries.kotlin)
    implementation(Libraries.androidCore)

    implementation(project(BuildModules.Shared))

    // Retrofit/Networking
    api(Libraries.retrofit)
    api(Libraries.okhttp)
    api(Libraries.gsonConvertor)
    api(Libraries.loggingInterceptor)

    implementation(Libraries.koinAndroid)
}
