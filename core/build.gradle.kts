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
        freeCompilerArgs = listOf("-Xjvm-default=compatibility")
    }
    buildFeatures.viewBinding = true
}

dependencies {
    // Hilt
    implementation(Deps.Hilt.android)
    kapt(Deps.Hilt.compiler)
    // Lifecycle
    implementation(Deps.Lifecycle.viewmodel)
    implementation(Deps.Lifecycle.livedata)
    implementation(Deps.Lifecycle.runtime)
    // Navigation
    implementation(Deps.Navigation.fragment)
    implementation(Deps.Navigation.ui)
    // Coroutines
    implementation(Deps.Coroutines.core)
    implementation(Deps.Coroutines.android)
    // UI
    implementation(Deps.UI.constraintLayout)
    implementation(Deps.UI.recyclerView)
    implementation(Deps.UI.progressbar)
    implementation(Deps.UI.fragmentKtx)
    implementation(Deps.UI.activityKtx)
    // Android
    implementation(Deps.Android.coreKtx)
    implementation(Deps.Android.appCompat)
    implementation(Deps.Android.appCompatResources)
    implementation(Deps.Android.material)
    // Test
    testImplementation(Deps.Test.jUnit)
    androidTestImplementation(Deps.Test.androidJUnit)
    androidTestImplementation(Deps.Test.espresso)
    androidTestImplementation(Deps.Test.mockitoCore)
    androidTestImplementation(Deps.Test.mockitoKotlin)
}