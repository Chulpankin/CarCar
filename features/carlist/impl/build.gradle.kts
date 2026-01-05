plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "com.example.carlist.impl"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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

    buildFeatures {
        compose = true
        viewBinding = true
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

    implementation(project(":core:common"))
    implementation(project(":core:data:api"))
    implementation(project(":core:ui"))
    implementation(project(":features:carlist:api"))
    implementation(project(":features:favorites:api"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.android)

    implementation(libs.material)
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.material.icons.extended)

    implementation(libs.coil)
    implementation(libs.coil.compose)

    implementation(libs.androidx.paging.common.android)
    implementation (libs.androidx.paging.compose)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    ksp(libs.dagger.compiler)
    implementation(libs.google.dagger)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
}