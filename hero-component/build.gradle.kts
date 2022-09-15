plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
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
}

dependencies {
    implementation(project(":core"))
    implementation(project(":hero-component"))
    // Coroutines
    implementation(Deps.Coroutines.core)
    implementation(Deps.Coroutines.android)
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