plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt") //agregamos el que genera los objetos
    id("com.google.dagger.hilt.android") //agregamos dagger
    id ("androidx.navigation.safeargs.kotlin") //activamos la dependencia
}

android {
    namespace = "com.example.cursokotlinintermedio"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cursokotlinintermedio"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false;
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro");
            buildConfigField("String", "BASE_URL", "\"https://newastro.vercel.app/\"");
        }
        getByName("debug") {
            isDebuggable = true;
            buildConfigField("String", "BASE_URL", "\"https://newastro.vercel.app/\"");
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true;
        buildConfig = true;
    }
}

dependencies {

    //Navigation Component
    val navComponent = "2.7.1";
    val cameraVersion = "1.2.3"
    implementation("androidx.navigation:navigation-fragment-ktx:$navComponent")
    implementation("androidx.navigation:navigation-ui-ktx:$navComponent")

    //DaggerHilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48") //Este auto genera el codigo

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.3.1") //liberia solo para en producction

    //Camera X
    implementation ("androidx.camera:camera-core:${cameraVersion}")
    implementation ("androidx.camera:camera-camera2:${cameraVersion}")
    implementation ("androidx.camera:camera-lifecycle:${cameraVersion}")
    implementation ("androidx.camera:camera-view:${cameraVersion}")
    implementation ("androidx.camera:camera-extensions:${cameraVersion}")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}