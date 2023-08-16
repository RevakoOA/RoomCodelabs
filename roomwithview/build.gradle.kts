plugins {
    id("kotlin-kapt")
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.ostapr.roomwithview"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.ostapr.roomwithview"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }

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

    packaging {
        resources.excludes.add("META-INF/atomicfu.kotlin_module")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)

    // Room components
    implementation(libs.androidx.room)
    kapt(libs.androidx.room.compiler)
    androidTestImplementation(libs.androidx.room.testing)


    // Lifecycle components
    implementation(libs.androidx.lifecycle.common)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Kotlin components
    implementation(libs.jetbrains.kotlin.jdk)
    api(libs.jetbrains.kotlin.coroutines.core)
    api(libs.jetbrains.kotlin.coroutines.android)

    // UI
    implementation(libs.androidx.constraintlayout)
    implementation(libs.android.material)

    // Testing
    testImplementation(libs.testing.junit)
    androidTestImplementation(libs.testing.androidx.core)
    androidTestImplementation(libs.testing.androidx.espresso.core) {
        exclude(group = "com.android.support", module = "support-annotations")
    }
    androidTestImplementation(libs.testing.androidx.junit.ext)
}