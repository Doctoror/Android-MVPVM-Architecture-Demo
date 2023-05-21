plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.doctoror.splittor"

    compileSdk = libs.versions.androidCompileSdkVersion.get().toInt()

    defaultConfig {
        applicationId = "com.doctoror.splittor"

        minSdk = libs.versions.androidMinSdkVersion.get().toInt()
        targetSdk = libs.versions.androidTargetSdkVersion.get().toInt()

        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
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

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
}

dependencies {
    implementation(platform(libs.compose.bom))

    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.koin)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.reflect)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.kotlin)
}
