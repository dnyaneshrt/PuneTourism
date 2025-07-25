plugins {
    alias(libs.plugins.android.application)
}

android {

    namespace = "com.infeanet.punetourism"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.infeanet.punetourism"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}
//allprojects {
//    repositories {
//
//        maven { url "https://jitpack.io" }// for toasty
//    }
//
//}

dependencies {

    implementation ("com.airbnb.android:lottie:6.6.6")
//    implementation ("com.github.GrenderG:Toasty:1.5.2")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}