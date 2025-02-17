plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-parcelize")
}

android {
    namespace = "com.blueprint.composecrossdrill"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.blueprint.composecrossdrill"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        buildConfig = true
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "environment"

    productFlavors {
        create("qa") {
            dimension = "environment"
            applicationIdSuffix = ".staging"
            applicationId = "com.blueprint.composecrossdrill.qa"
            resValue("string", "app_name", "MyApp (QA)")
            buildConfigField("String", "API_BASE_URL", "\"https://dummyjson.com/\"")
        }

        create("uat") {
            dimension = "environment"
            applicationIdSuffix = ".preprod"
            applicationId = "com.blueprint.composecrossdrill.uat"
            resValue("string", "app_name", "MyApp (UAT)")
            buildConfigField("String", "API_BASE_URL", "\"https://dummyjson.com/\"")
        }

        create("prod") {
            dimension = "environment"
            applicationId = "com.blueprint.composecrossdrill"
            resValue("string", "app_name", "MyApp")
            buildConfigField("String", "API_BASE_URL", "\"https://dummyjson.com/\"")
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
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.gson)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //Image loading dependencies
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    //Koin- dependency injection
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    //Koin- Jetpack Compose
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.compose.navigation)

    implementation(libs.com.squareup.retrofit2.retrofit)
    implementation(libs.com.squareup.retrofit2.converter.gson)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}