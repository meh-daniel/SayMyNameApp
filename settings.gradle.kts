pluginManagement {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.android")) {
                useModule("com.android.tools.build:gradle:7.0.2")
            }
            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
                useVersion("1.5.31")
            }
            if (requested.id.id.startsWith("dagger.hilt.android")) {
                useModule("com.google.dagger:hilt-android-gradle-plugin:2.43.2")
            }
        }
    }
}
rootProject.name = "SayMyNameApp"
include(
    ":app",
    ":core",
    ":core-ui",
    ":serial-component",
    ":serial-component-impl",
    ":feature-main",
)
