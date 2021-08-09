import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsTargetDsl

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    jvm("desktop")
    android()
    // ios()

    js(IR, KotlinJsTargetDsl::browser)

    sourceSets {
        named("commonTest") {
            dependencies {
                implementation(kotlinTestCommon)
            }
        }

        named("androidTest") {
            dependencies {
                implementation(kotlinTestJUnit)
            }
        }
        named("desktopTest") {
            dependencies {
                implementation(kotlinTestJUnit)
            }
        }
        named("jsTest") {
            dependencies {
                implementation(kotlinTestJs)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}