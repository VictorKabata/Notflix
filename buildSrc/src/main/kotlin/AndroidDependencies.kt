object AndroidDependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val androidCore = "androidx.core:core-ktx:${Versions.androidCore_version}"

    const val firebaseBOM = "com.google.firebase:firebase-bom:${Versions.firebase_bom_version}"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"

    const val material = "com.google.android.material:material:${Versions.material_version}"

    const val composeUI = "androidx.compose.ui:ui:${Versions.compose_version}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose_version}"
    const val composeTooling = "androidx.compose.ui:ui-tooling-preview:${Versions.compose_version}"
    const val composeRuntime =
        "androidx.compose.runtime:runtime-livedata:${Versions.compose_version}"
    const val composeUtil = "androidx.compose.ui:ui-util:${Versions.compose_version}"
    const val composeConstraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.compose_constraint_version}"
    const val composeActivity =
        "androidx.activity:activity-compose:${Versions.compose_activity_version}"

    const val accompanistPager =
        "com.google.accompanist:accompanist-pager:${Versions.accompanist_version}"
    const val accompanistInsets =
        "com.google.accompanist:accompanist-insets:${Versions.accompanist_version}"
    const val accompanistAnimation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist_version}"
    const val accompanistSystemUIController =
        "com.google.accompanist:accompanist-systemuicontroller:0.17.0"
    const val accompanistMaterialPlaceHolder =
        "com.google.accompanist:accompanist-placeholder-material:${Versions.accompanist_version}"
    const val accompanistPagerIndicator =
        "com.google.accompanist:accompanist-pager-indicators:0.22.0-rc"

    const val lifecycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"

    // Koin-Dependency injection
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin_version}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin_version}"

    // Compose Navigation-Navigation between various screens
    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.navigation_version}"

    // Coil-Image Loader
    const val coil = "io.coil-kt:coil-compose:${Versions.coil_version}"

    // Palette-Used to extract color palette from images
    const val palette = "com.android.support:palette-v7:${Versions.palette_version}"

    // Gowtham Compose Rating Bar
    const val ratingBar = "com.github.a914-gowtham:compose-ratingbar:${Versions.rating_bar_version}"

    // Test Libs
    const val jUnit = "junit:junit:${Versions.jUnit_version}"
    const val googleTruth = "com.google.truth:truth:${Versions.truth_version}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest_version}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric_version}"
    const val jUnitKtx = "androidx.test.ext:junit-ktx:${Versions.jUnitKtx}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val testCore = "androidx.test:core-ktx:${Versions.testCore}"
    const val archTestCore = "androidx.arch.core:core-testing:${Versions.archTestCore}"
    const val testRules = "androidx.test:rules:${Versions.testRules}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
}
