object Libraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
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
    const val accompanistPermissions =
        "com.google.accompanist:accompanist-permissions:${Versions.accompanist_version}"
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

    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"

    // Compose Navigation-Navigation between various screens
    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.navigation_version}"

    // Coil-Image Loader
    const val coil = "io.coil-kt:coil-compose:${Versions.coil_version}"

    // Palette-Used to extract color palette from images
    const val palette = "com.android.support:palette-v7:${Versions.palette_version}"

    // Gowtham Compose Rating Bar
    const val ratingBar = "com.github.a914-gowtham:compose-ratingbar:${Versions.rating_bar_version}"

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room_version}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room_version}"

    // DataStore
    const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore_version}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor_version}"
    const val gsonConvertor = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"

    // Paging 3
    const val paging = "androidx.paging:paging-runtime:${Versions.paging_version}"
    const val pagingCompose = "androidx.paging:paging-compose:${Versions.paging_compose_version}"

    // Test Libs
    const val jUnit = "junit:junit:${Versions.jUnit_version}"
    const val googleTruth = "com.google.truth:truth:${Versions.truth_version}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest_version}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric_version}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer_version}"
    const val jUnitKtx = "androidx.test.ext:junit-ktx:${Versions.jUnitKtx}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val testCore = "androidx.test:core-ktx:${Versions.testCore}"
    const val archTestCore = "androidx.arch.core:core-testing:${Versions.archTestCore}"
    const val testRules = "androidx.test:rules:${Versions.testRules}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
}
