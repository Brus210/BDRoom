plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

}

android {
    namespace = "com.example.basededatos"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.basededatos"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    //implementacion para el uso de view models
    var lifecycle_version= "2.4.0"
    var room_version = "2.4.0"

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


   
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version")
// LiveData
implementation("androidx.lifecycle:lifecycle-livedata:$lifecycle_version")
// Lifecycles only (without ViewModel or LiveData)
implementation("androidx.lifecycle:lifecycle-runtime:$lifecycle_version")
// Saved state module for ViewModel
implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")
// Annotation processor
annotationProcessor("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
// optional - helpers for implementing LifecycleOwner in a Service
implementation("androidx.lifecycle:lifecycle-service:$lifecycle_version")
// optional - ProcessLifecycleOwner provides a lifecycle for the whole application process
implementation("androidx.lifecycle:lifecycle-process:$lifecycle_version")
// optional - ReactiveStreams support for LiveData
implementation("androidx.lifecycle:lifecycle-reactivestreams:$lifecycle_version")
implementation("androidx.room:room-runtime:$room_version")
annotationProcessor("androidx.room:room-compiler:$room_version")}
