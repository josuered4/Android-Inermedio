plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt") //agregamos el que genera los objetos
    id("com.google.dagger.hilt.android") //agregamos dagger
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
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding {
        enable=true
    }
}

dependencies {

    //Navigation Component
    val navComponent = "2.7.1";
    implementation("androidx.navigation:navigation-fragment-ktx:$navComponent")
    implementation("androidx.navigation:navigation-ui-ktx:$navComponent")

    //Dagger Hilt
    val daggerHilt = "2.48";
    implementation ("com.google.dagger:hilt-android:$daggerHilt") //inyecta
    kapt ("com.google.dagger:hilt-android-compiler:$daggerHilt") //Este auto genera el codigo

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}