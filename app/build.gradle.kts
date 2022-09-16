plugins {
    id(Plugins.AGP.APPLICATION)
    id(Plugins.HILT)
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
        applicationId = "meh.daniel.com.saymynameapp"
        minSdk =  Config.minSDK
        targetSdk = Config.targetSDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas",)
            arg("room.incremental", "true")
            arg("room.expandProjection", "true")
        }
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildFeatures.viewBinding = true
}

dependencies {
    implementation(project(Modules.Components.HERO))
    implementation(project(Modules.Core.CORE))
    implementation(project(Modules.Features.HERO_LIST))
    implementation(project(Modules.Features.HERO_DETAIL_INFO))
    // Network
    implementation(Deps.Network.retrofit2)
    implementation(Deps.Network.retrofit2Gson)
    implementation(Deps.Network.logging)
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