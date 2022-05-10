buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(ProjectLevelPlugins.gradle)
        classpath(ProjectLevelPlugins.kotlin)
        classpath(ProjectLevelPlugins.firebaseCrashlyitics)
        classpath(ProjectLevelPlugins.googleServices)
    }
}

allprojects {

    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }

}

tasks.register("clean").configure {
    delete("build")
}