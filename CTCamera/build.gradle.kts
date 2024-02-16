plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.ct.mycameralibray"
    compileSdk = ctlibs.versions.compileSDK.get().toInt()

    defaultConfig {
        minSdk = ctlibs.versions.minSDK.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding =true
        dataBinding =true
    }
}

dependencies {

    implementation(ctlibs.androidx.core)
    implementation(ctlibs.androidx.appcompat)
    implementation (ctlibs.android.material)

    implementation (ctlibs.retrofit.gson)
    implementation (ctlibs.glide)
    implementation (ctlibs.google.location)
    implementation (ctlibs.camera.core)
    implementation (ctlibs.camera.camera2)
    implementation (ctlibs.camera.lifecycle)
    implementation (ctlibs.camera.view)

    testImplementation (ctlibs.testJunit)
    androidTestImplementation (ctlibs.testExt)
    androidTestImplementation (ctlibs.testEspress)



}