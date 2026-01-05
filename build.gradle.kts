plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.devtools.ksp) apply false
    alias(libs.plugins.android.dynamic.feature) apply false
    alias(libs.plugins.secrets.gradle.plugin) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.gms.google.services) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.firebase.performance) apply false
    alias(libs.plugins.detekt) apply true
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom("$projectDir/detekt.yml")
    baseline = file("$projectDir/detekt-baseline.xml")
    
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(false)
        sarif.required.set(false)
    }
}