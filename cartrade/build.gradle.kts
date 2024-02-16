plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.ct.ctcameralib"
    compileSdk = ctlibs.versions.compileSDK.get().toInt()

    defaultConfig {
        applicationId = "com.ct.ctcameralib"
        minSdk = ctlibs.versions.minSDK.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }


    buildFeatures {
        viewBinding = true
        dataBinding =true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {

    implementation(project(mapOf("path" to ":ctcamera")))
    implementation(project(mapOf("path" to ":sharedutils")))


    implementation(ctlibs.androidx.core)
    implementation(ctlibs.androidx.appcompat)
    implementation (ctlibs.android.material)
    implementation(ctlibs.androidx.constraintlayout)
    implementation (ctlibs.retrofit.gson)
    implementation (ctlibs.glide)

    testImplementation (ctlibs.testJunit)
    androidTestImplementation (ctlibs.testExt)
    androidTestImplementation (ctlibs.testEspress)
}