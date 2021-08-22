import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsTargetDsl

plugins {
    kotlin("multiplatform")
    id("android-multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm("desktop")
    android()

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("androidx.compose.material.ExperimentalMaterialApi")
            }
        }

        named("commonMain") {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.materialIconsExtended)
            }
        }

        named("androidMain") {
            dependencies {
                implementation(Libraries.Jetpack.appCompat)
                implementation(Libraries.Jetpack.core)
            }
        }

        named("desktopMain") {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
