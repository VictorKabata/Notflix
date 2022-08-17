plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.android)

    id(Plugins.googleService)
    id(Plugins.appDistribution)
    id(Plugins.crashlytics)
    id(Plugins.performance)
}

android {
    compileSdk = AndroidSdk.compileSdkVersion

    defaultConfig {
        applicationId = AndroidSdk.applicationId

        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion
        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName

        testInstrumentationRunner = AndroidSdk.testInstrumentationRunner
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    api(project(BuildModules.shared))

    implementation(AndroidDependencies.androidCore)

    implementation(AndroidDependencies.material)

    implementation(AndroidDependencies.composeUI)
    implementation(AndroidDependencies.composeMaterial)
    implementation(AndroidDependencies.composeTooling)
    implementation(AndroidDependencies.composeRuntime)
    implementation(AndroidDependencies.composeUtil)
    implementation(AndroidDependencies.composeConstraintLayout)
    implementation(AndroidDependencies.composeActivity)

    implementation(AndroidDependencies.accompanistPager)
    implementation(AndroidDependencies.accompanistInsets)
    implementation(AndroidDependencies.accompanistAnimation)
    implementation(AndroidDependencies.accompanistSystemUIController)
    implementation(AndroidDependencies.accompanistMaterialPlaceHolder)
    implementation(AndroidDependencies.accompanistPagerIndicator)

    implementation(AndroidDependencies.lifecycleRuntime)

    // Koin-Dependency injection
    implementation(AndroidDependencies.koinAndroid)
    implementation(AndroidDependencies.koinCompose)

    // Compose Navigation-Navigation between various screens
    implementation(AndroidDependencies.composeNavigation)

    // Coil-Image Loader
    implementation(AndroidDependencies.coil)

    // Palette-Used to extract color palette from images
    implementation(AndroidDependencies.palette)

    // Gowtham Compose Rating Bar
    implementation(AndroidDependencies.ratingBar)

    // Leak Canary - Memory leaks
    debugImplementation(AndroidDependencies.leakCanary)

    // Firebase
    implementation(platform(AndroidDependencies.firebaseBOM))
    implementation(AndroidDependencies.firebaseAnalytics)
    implementation(AndroidDependencies.firebaseCrashlytics)
    implementation(AndroidDependencies.firebasePerformance)

    testImplementation(AndroidDependencies.jUnit)
    testImplementation(AndroidDependencies.googleTruth)
    testImplementation(AndroidDependencies.coroutinesTest)

    androidTestImplementation(AndroidDependencies.testRules)
    androidTestImplementation(AndroidDependencies.testRunner)
}
