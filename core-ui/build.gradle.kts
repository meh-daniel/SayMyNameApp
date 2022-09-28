plugins {
    id(Plugins.AGP.LIBRARY)
    kotlin(Plugins.Kotlin.ANDROID)
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
    }
    compileOptions {
        sourceCompatibility = Config.Options.compileOptions
        targetCompatibility = Config.Options.compileOptions
    }
    kotlinOptions {
        jvmTarget = Config.Options.kotlinOptions
    }
    buildFeatures.viewBinding = true
}

dependencies {
    // UI
    implementation(Deps.UI.CONSTRAINT_LAYOUT)
    implementation(Deps.UI.RECYCLER_VIEW)
    implementation(Deps.UI.PROGRESS_BAR)
    implementation(Deps.UI.FRAGMENT_KTX)
    implementation(Deps.UI.ACTIVITY_KTX)
    // Android
    implementation(Deps.Android.CORE_KTX)
    implementation(Deps.Android.APPCOMPAT)
    implementation(Deps.Android.MATERIAL)
    // Test
    testImplementation(Deps.Test.JUNIT)
    androidTestImplementation(Deps.Test.ANDROID_JUNIT)
    androidTestImplementation(Deps.Test.ESPRESSO)
    androidTestImplementation(Deps.Test.MOCKITO_CORE)
    androidTestImplementation(Deps.Test.MOCKITO_KOTLIN)
}