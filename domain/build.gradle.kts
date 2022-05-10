plugins {
    id(BuildPlugins.java)
    id(BuildPlugins.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation(Libraries.kotlin)
    implementation(Libraries.coroutinesCore)
}