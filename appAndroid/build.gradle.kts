plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.googleServices.plugin)
    alias(libs.plugins.firebase.appDistribution.plugin)
    alias(libs.plugins.firebase.crashlytics.plugin)
    alias(libs.plugins.firebase.performance.plugin)
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vickikbt.notflix"

        minSdk = 24
        targetSdk = compileSdk
        versionCode = 1
        versionName = "1.0.0"

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

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    namespace = "com.vickikbt.notflix"
}

dependencies {
    api(project(":shared"))

    implementation(libs.androidX.core)

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

    testImplementation(libs.jUnitKtx)
    testImplementation(libs.kotlinX.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.archTestCore)
    testImplementation(libs.robolectric)

    androidTestImplementation(libs.test.rules)
    androidTestImplementation(libs.test.runner)
}
