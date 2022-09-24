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
    implementation(project(Modules.Components.HERO_IMPL))
    implementation(project(Modules.Main.CORE))
    implementation(project(Modules.Main.CORE_UI))
    implementation(project(Modules.Features.MAIN))
    // Network
    implementation(Deps.Network.RETROFIT2)
    implementation(Deps.Network.RETROFIT2_GSON)
    implementation(Deps.Network.LOGGING_INERCEPTOR)
    // Hilt
    implementation(Deps.Hilt.ANDROID)
    kapt(Deps.Hilt.COMPILER)
    // Room
    implementation(Deps.Room.KTX)
    implementation(Deps.Room.RUNTIME)
    kapt(Deps.Room.COMPILER)
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