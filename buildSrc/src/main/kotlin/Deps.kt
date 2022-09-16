object Deps {
    object Hilt {
        const val ANDROID = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val COMPILER = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    }

    object Lifecycle {
        const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }

    object Javax {
        const val INJECT = "javax.inject:javax.inject:1"
    }

    object Room {
        const val KTX = "androidx.room:room-ktx:${Versions.room}"
        const val RUNTIME = "androidx.room:room-runtime:${Versions.room}"
        const val COMPILER = "androidx.room:room-compiler:${Versions.room}"
    }

    object Navigation {
        const val FRAGMENT = "androidx.navigation:navigation-fragment:${Versions.navigation}"
        const val UI = "androidx.navigation:navigation-ui:${Versions.navigation}"
    }

    object Coroutines {
        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Network {
        const val RETROFIT2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
        const val RETROFIT2_GSON = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"
        const val LOGGING_INERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.logging}"
    }

    object ImageLoad {
        const val GLIDE = "com.github.bumptech.glide:glide:4.13.2"
        const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:4.13.2"
    }

    object UI {
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val PROGRESS_BAR = "com.mikhaellopez:circularprogressbar:${Versions.progressbar}"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    }

    object Android {
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.androidCoreKtx}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.androidAppcompat}"
        const val MATERIAL = "com.google.android.material:material:${Versions.androidMaterial}"
    }

    object Test {
        const val JUNIT = "junit:junit:${Versions.testJUnit}"
        const val ANDROID_JUNIT = "androidx.test.ext:${Versions.testJUnit}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.testEspresso}"
        const val MOCKITO_CORE = "org.mockito:mockito-core:${Versions.mockito}"
        const val MOCKITO_KOTLIN = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito}"
    }
}