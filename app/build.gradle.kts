plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.rowiosama.bohemianhijabapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.rowiosama.bohemianhijabapp"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //ui
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.recyclerview:recyclerview:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.9.0")

    implementation("com.github.bumptech.glide:glide:4.15.1")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.fragment:fragment-ktx:1.6.0")

    //testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //room
    implementation("androidx.room:room-runtime:2.5.1")
    ksp("androidx.room:room-compiler:2.5.1")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    //coroutine support
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1") //viewModelScope
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1") //liveData
    implementation("androidx.room:room-ktx:2.5.1")

    //compose
    implementation (platform("androidx.compose:compose-bom:2023.05.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.runtime:runtime-livedata") //observeAsState
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1") //ConstraintLayout
    implementation("io.coil-kt:coil-compose:2.2.2") //AsyncImage
    implementation ("com.google.accompanist:accompanist-pager:0.12.0") // pager
    implementation ("androidx.navigation:navigation-compose:2.6.0")
}