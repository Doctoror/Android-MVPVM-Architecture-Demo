plugins {
    id("com.android.library")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.doctoror.splittor.data"

    compileSdk = libs.versions.androidCompileSdkVersion.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidMinSdkVersion.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":domain"))

    ksp(libs.room.compiler)

    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    implementation(libs.koin)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.kotlin)

    androidTestImplementation(libs.test.core)
    androidTestImplementation(libs.test.runner)
}
