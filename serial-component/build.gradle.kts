plugins {
    id(Plugins.JAVA)
    id(Plugins.JVM)
}
java {
    sourceCompatibility = Config.Options.compileOptions
    targetCompatibility = Config.Options.compileOptions
}
dependencies {
    // Coroutines
    implementation(Deps.Coroutines.CORE)
    // Di
    implementation(Deps.Javax.INJECT)
}