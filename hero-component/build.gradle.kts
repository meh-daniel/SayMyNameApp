plugins {
    id(Plugins.AGP.LIBRARY)
    kotlin(Plugins.Kotlin.ANDROID)
    kotlin(Plugins.Kotlin.KAPT)
}

repositories {
    mavenCentral()
    google()
}

android {
    compileSdk = Config.compileSdk
    defaultConfig {
        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        minSdk =  Config.minSDK
        targetSdk = Config.targetSDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = Config.Options.compileOptions
        targetCompatibility = Config.Options.compileOptions
    }
    kotlinOptions {
        jvmTarget = Config.Options.kotlinOptions
    }
}

dependencies {
    implementation(project(Modules.Core.CORE))
    // Coroutines
    implementation(Deps.Coroutines.core)
    implementation(Deps.Coroutines.android)
    // Di
    implementation(Deps.Javax.inject)
    // Network
    implementation(Deps.Network.retrofit2)
    implementation(Deps.Network.retrofit2Gson)
    implementation(Deps.Network.logging)
    // Android
    testImplementation(Deps.Test.jUnit)
    androidTestImplementation(Deps.Test.androidJUnit)
    androidTestImplementation(Deps.Test.espresso)
    androidTestImplementation(Deps.Test.mockitoCore)
    androidTestImplementation(Deps.Test.mockitoKotlin)
}