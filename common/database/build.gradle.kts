plugins {
    kotlin("multiplatform")
    `android-multiplatform`
}

kotlin {
    jvm("desktop")
    android()
    // ios()

    sourceSets {
        named("androidMain") {
            dependencies {
                implementation(Libraries.SQLDelight.jvm)
                implementation(Libraries.SQLDelight.android)
            }
        }
        named("desktopMain") {
            dependencies {
                implementation(Libraries.SQLDelight.jvm)
            }
        }
        /*
        named("iosMain") {
            dependencies {
                implementation(Libraries.SQLDelight.ios)
            }
        }
        */
    }
}
