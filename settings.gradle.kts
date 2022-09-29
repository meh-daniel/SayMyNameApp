pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
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
