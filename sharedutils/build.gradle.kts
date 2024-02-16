plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.demo.sharedmethods"
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
}

dependencies {

    implementation(ctlibs.androidx.core)
    implementation(ctlibs.androidx.appcompat)
    implementation(ctlibs.android.material)
    testImplementation (ctlibs.testJunit)
    androidTestImplementation (ctlibs.testExt)
    androidTestImplementation (ctlibs.testEspress)

}