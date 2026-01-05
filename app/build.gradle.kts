plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.gms.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.firebase.performance)
}

android {
    namespace = "com.example.carcar"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.carcar"
        minSdk = 24
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(project(":core:common"))
    implementation(project(":core:data:api"))
    implementation(project(":core:data:impl:firebase"))
    implementation(project(":core:data:impl:network"))
    implementation(project(":core:ui"))
    implementation(project(":features:signin:api"))
    implementation(project(":features:signin:impl"))
    implementation(project(":features:signup:api"))
    implementation(project(":features:signup:impl"))
    implementation(project(":features:carlist:api"))
    implementation(project(":features:carlist:impl"))
    implementation(project(":features:carsearch:api"))
    implementation(project(":features:carsearch:impl"))
    implementation(project(":features:favorites:api"))
    implementation(project(":features:favorites:impl"))
    implementation(libs.ktor.client.core)
    ksp(libs.dagger.compiler)
    implementation(libs.google.dagger)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.perf)
    implementation(libs.androidx.paging.common.android)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}