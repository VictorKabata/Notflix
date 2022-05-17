object ProjectLevelPlugins {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle_version}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleService_version}"
    const val firebaseCrashlyitics = "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlytics_version}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    const val ktLint = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlint_version}"
    const val sqlDelight = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
    const val kmpNativeCoroutines =
        "com.rickclephas.kmp:kmp-nativecoroutines-gradle-plugin:${Versions.kmpNativeCoroutines}"
}
