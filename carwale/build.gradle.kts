plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.demo.hacktivatedemo"
    compileSdk = ctlibs.versions.compileSDK.get().toInt()

    defaultConfig {
        applicationId = "com.demo.hacktivatedemo"
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
    implementation(project(mapOf("path" to ":sharedutils")))
    implementation(project(mapOf("path" to ":ctcamera")))

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