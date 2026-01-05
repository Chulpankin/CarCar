plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.data.impl.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        val baseUrl = project.findProperty("BASE_URL") as String? ?: "https://www.carqueryapi.com/api/0.3"
        val connectTimeout = project.findProperty("CONNECT_TIMEOUT") as String? ?: "120000"
        val socketTimeout = project.findProperty("SOCKET_TIMEOUT") as String? ?: "120000"
        
        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        buildConfigField("int", "CONNECT_TIMEOUT", connectTimeout)
        buildConfigField("int", "SOCKET_TIMEOUT", socketTimeout)
    }

    buildFeatures {
        buildConfig = true
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
    implementation(libs.androidx.core.ktx)

    implementation(project(":core:data:api"))
    implementation(project(":core:common"))

    api(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.slf4j.simple)

    ksp(libs.dagger.compiler)
    implementation(libs.google.dagger)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
}

