plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.googleServices)
    id(BuildPlugins.firebaseCrashlytics)
}

android {
    compileSdk = AndroidSDK.compileSdkVersion
    buildToolsVersion(AndroidSDK.buildToolVersion)

    defaultConfig {
        applicationId = AndroidSDK.applicationId

        minSdk = AndroidSDK.minSdkVersion
        targetSdk = AndroidSDK.targetSdkVersion
        versionCode = AndroidSDK.versionCode
        versionName = AndroidSDK.versionName

        testInstrumentationRunner = AndroidSDK.testInstrumentationRunner

        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            // proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_version
    }
}

dependencies {
    implementation("com.android.support:multidex:2.0.1")

    api(project(BuildModules.Shared))
    api(project(BuildModules.Cache))

    implementation(AndroidDependencies.kotlin)
    implementation(AndroidDependencies.androidCore)

    implementation(platform(AndroidDependencies.firebaseBOM))
    implementation(AndroidDependencies.firebaseAnalytics)
    implementation(AndroidDependencies.firebaseCrashlytics)

    implementation(AndroidDependencies.material)

    implementation(AndroidDependencies.composeUI)
    implementation(AndroidDependencies.composeMaterial)
    implementation(AndroidDependencies.composeTooling)
    implementation(AndroidDependencies.composeRuntime)
    implementation(AndroidDependencies.composeUtil)
    implementation(AndroidDependencies.composeConstraintLayout)
    implementation(AndroidDependencies.composeActivity)

    implementation(AndroidDependencies.accompanistPager)
    implementation(AndroidDependencies.accompanistPermissions)
    implementation(AndroidDependencies.accompanistInsets)
    implementation(AndroidDependencies.accompanistAnimation)
    implementation(AndroidDependencies.accompanistSystemUIController)
    implementation(AndroidDependencies.accompanistMaterialPlaceHolder)
    implementation(AndroidDependencies.accompanistPagerIndicator)

    implementation(AndroidDependencies.lifecycleLiveData)
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

    testImplementation(AndroidDependencies.jUnit)
    testImplementation(AndroidDependencies.googleTruth)
    testImplementation(AndroidDependencies.coroutinesTest)

    androidTestImplementation(AndroidDependencies.testRules)
    androidTestImplementation(AndroidDependencies.testRunner)
}
