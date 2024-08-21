plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.googleServices.plugin)
    alias(libs.plugins.firebase.appDistribution.plugin)
    alias(libs.plugins.firebase.crashlytics.plugin)
    alias(libs.plugins.firebase.performance.plugin)
    alias(libs.plugins.compose.compiler)
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vickbt.notflix"

        minSdk = 24
        targetSdk = compileSdk
        versionCode = if (System.getenv("VERSION_CODE").isNullOrEmpty()) {
            1
        } else {
            System.getenv("VERSION_CODE").toInt()
        }
        versionName = if (System.getenv("VERSION_NAME").isNullOrEmpty()) {
            "1.0.0"
        } else {
            System.getenv("VERSION_NAME")?.toString()
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    namespace = "com.vickbt.notflix"
}

dependencies {
    api(project(":shared"))

    implementation(libs.androidX.core)
    implementation(libs.androidX.activity)

    implementation(libs.material)

    // Koin-Dependency injection
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    // Leak Canary - Memory leaks
    debugImplementation(libs.leakCanary)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.performance)
}
