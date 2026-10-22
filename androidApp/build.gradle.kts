plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.googleServices.plugin)
    alias(libs.plugins.firebase.appDistribution.plugin)
    alias(libs.plugins.firebase.crashlytics.plugin)
    alias(libs.plugins.firebase.performance.plugin)
}

android {
    namespace = "com.vickbt.notflix"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.vickbt.notflix"

        minSdk = 24
        targetSdk = 37
        versionCode = System.getenv("VERSION_CODE")?.takeIf { it.isNotEmpty() }?.toInt() ?: 1
        versionName = System.getenv("VERSION_NAME")?.takeIf { it.isNotEmpty() } ?: "1.0.0"

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
}

dependencies {
    implementation(project(":composeApp"))

    implementation(libs.compose.runtime)
    implementation(libs.androidX.activity)
    implementation(libs.koin.android)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.performance)

    debugImplementation(libs.leakCanary)
}
