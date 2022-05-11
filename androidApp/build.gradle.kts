plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.googleServices)
    id(BuildPlugins.firebaseCrashlytics)
}

android {
    compileSdkVersion(AndroidSDK.compileSdkVersion)
    buildToolsVersion(AndroidSDK.buildToolVersion)

    defaultConfig {
        applicationId = AndroidSDK.applicationId

        minSdkVersion(AndroidSDK.minSdkVersion)
        targetSdkVersion(AndroidSDK.targetSdkVersion)
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
        useIR = true
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "${Versions.compose_version}"
        kotlinCompilerVersion = "1.5.21"
    }

    /*packagingOptions {
        resources {
            excludes += '/META-INF/{AL2.0,LGPL2.1}'
        }
    }*/
}

dependencies {
    implementation("com.android.support:multidex:2.0.1")

    api(project(BuildModules.Domain))
    api(project(BuildModules.Repository))
    api(project(BuildModules.Shared))

    implementation(Libraries.kotlin)
    implementation(Libraries.androidCore)

    implementation(platform(Libraries.firebaseBOM))
    implementation(Libraries.firebaseAnalytics)
    implementation(Libraries.firebaseCrashlytics)

    implementation(Libraries.material)

    implementation(Libraries.composeUI)
    implementation(Libraries.composeMaterial)
    implementation(Libraries.composeTooling)
    implementation(Libraries.composeRuntime)
    implementation(Libraries.composeUtil)
    implementation(Libraries.composeConstraintLayout)
    implementation(Libraries.composeActivity)

    implementation(Libraries.accompanistPager)
    implementation(Libraries.accompanistPermissions)
    implementation(Libraries.accompanistInsets)
    implementation(Libraries.accompanistAnimation)
    implementation(Libraries.accompanistSystemUIController)
    implementation(Libraries.accompanistMaterialPlaceHolder)
    implementation(Libraries.accompanistPagerIndicator)

    implementation(Libraries.lifecycleLiveData)
    implementation(Libraries.lifecycleRuntime)

    // Koin-Dependency injection
    implementation(Libraries.koinAndroid)
    implementation(Libraries.koinCompose)

    // Compose Navigation-Navigation between various screens
    implementation(Libraries.composeNavigation)

    // Coil-Image Loader
    implementation(Libraries.coil)

    // Palette-Used to extract color palette from images
    implementation(Libraries.palette)

    implementation(Libraries.paging)
    implementation(Libraries.pagingCompose)

    // Gowtham Compose Rating Bar
    implementation(Libraries.ratingBar)

    testImplementation(Libraries.jUnit)
    testImplementation(Libraries.googleTruth)
    testImplementation(Libraries.coroutinesTest)
    testImplementation(Libraries.mockWebServer)

    androidTestImplementation(Libraries.testRules)
    androidTestImplementation(Libraries.testRunner)
}
