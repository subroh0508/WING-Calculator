import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsTargetDsl

plugins {
    id("common-multiplatform")
}

kotlin {
    jvm("desktop")
    android()
    // ios()
    js(IR, KotlinJsTargetDsl::browser)

    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:utilities"))
                implementation(project(":common:database"))

                implementation(Libraries.Koin.core)
            }
        }
    }
}
