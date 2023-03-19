object AndroidSdk {
    const val appName = "Notflix"

    const val applicationId = "com.vickikbt.notflix"

    const val buildToolVersion = "30.0.3"

    const val minSdkVersion = 21
    const val compileSdkVersion = 33
    const val targetSdkVersion = compileSdkVersion

    const val versionCode = 3
    const val versionName = "1.02"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object BuildModules {
    const val shared = ":shared"
    const val androidApp = ":androidApp"
    const val iOSApp = ":iOSApp"
    const val desktopApp = ":desktopApp"
}

