object AndroidSdk {
    const val appName = "Notflix"

    const val applicationId = "com.vickikbt.notflix"

    const val buildToolVersion = "30.0.3"

    const val minSdkVersion = 21
    const val compileSdkVersion = 31
    const val targetSdkVersion = compileSdkVersion

    const val versionCode = 3
    const val versionName = "1.02"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object AndroidDependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val androidCore = "androidx.core:core-ktx:${Versions.androidCore}"

    const val material = "com.google.android.material:material:${Versions.material}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val composeUI = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeRuntime =
        "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val composeUtil = "androidx.compose.ui:ui-util:${Versions.compose}"
    const val composeConstraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.compose_constraint}"
    const val composeActivity =
        "androidx.activity:activity-compose:${Versions.compose_activity}"
    const val material3 = "androidx.compose.material3:material3:${Versions.composeMat3}"

    const val accompanistPager =
        "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
    const val accompanistInsets =
        "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
    const val accompanistAnimation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
    const val accompanistSystemUIController =
        "com.google.accompanist:accompanist-systemuicontroller:0.17.0"
    const val accompanistMaterialPlaceHolder =
        "com.google.accompanist:accompanist-placeholder-material:${Versions.accompanist}"
    const val accompanistPagerIndicator =
        "com.google.accompanist:accompanist-pager-indicators:0.22.0-rc"

    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    // Koin-Dependency injection
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"

    // Compose Navigation-Navigation between various screens
    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.navigation}"

    // Coil-Image Loader
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

    // Palette-Used to extract color palette from images
    const val palette = "com.android.support:palette-v7:${Versions.palette}"

    // Gowtham Compose Rating Bar
    const val ratingBar = "com.github.a914-gowtham:compose-ratingbar:${Versions.rating_bar}"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    //Firebase
    const val firebaseBOM = "com.google.firebase:firebase-bom:${Versions.firebase_bom}"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val firebasePerformance="com.google.firebase:firebase-perf"

    // Test Libs
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val googleTruth = "com.google.truth:truth:${Versions.truth}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val jUnitKtx = "androidx.test.ext:junit-ktx:${Versions.jUnitKtx}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val testCore = "androidx.test:core-ktx:${Versions.testCore}"
    const val archTestCore = "androidx.arch.core:core-testing:${Versions.archTestCore}"
    const val testRules = "androidx.test:rules:${Versions.testRules}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
}
