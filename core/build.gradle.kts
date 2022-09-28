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
        minSdk =  Config.minSDK
        targetSdk = Config.targetSDK

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
    // Hilt
    implementation(Deps.Hilt.ANDROID)
    kapt(Deps.Hilt.COMPILER)
    // Lifecycle
    implementation(Deps.Lifecycle.VIEW_MODEL)
    implementation(Deps.Lifecycle.LIVE_DATA)
    implementation(Deps.Lifecycle.RUNTIME)
    // Navigation
    implementation(Deps.Navigation.FRAGMENT)
    implementation(Deps.Navigation.UI)
    // Coroutines
    implementation(Deps.Coroutines.CORE)
    implementation(Deps.Coroutines.ANDROID)
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