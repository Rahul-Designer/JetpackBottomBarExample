plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.hilt.android)
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.company.jetpackbottombarexample"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.company.jetpackbottombarexample"
        minSdk = 24
        targetSdk = 36
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    /*
    // Material 3 adaptive layouts
    implementation(libs.androidx.material3.windowsizeclass)
    implementation(libs.androidx.adaptive.layout)
    implementation(libs.androidx.adaptive.layout.navigation)

     */

    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
//    implementation("androidx.navigation3:navigation3-runtime:1.1.0-alpha01")
//    implementation("androidx.navigation3:navigation3-ui:1.1.0-alpha01")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-navigation3:1.0.0-alpha01")

    // Kotlin serialization
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlinx.serialization.json)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    // Full set of material icons
    implementation(libs.androidx.material.icons.extended)


    // Material 3 with window size class support
//    implementation("androidx.compose.material3:material3:1.2.0")
//    implementation("androidx.compose.material3:material3-window-size-class")
//    implementation("androidx.compose.material3:material3-adaptive-navigation-suite")

    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)

    implementation("androidx.compose.material3.adaptive:adaptive")
    implementation("androidx.compose.material3.adaptive:adaptive-layout")
    implementation("androidx.compose.material3.adaptive:adaptive-navigation")

}