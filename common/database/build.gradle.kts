plugins {
    kotlin("multiplatform")
    `android-multiplatform`
    id("com.squareup.sqldelight")
}

kotlin {
    jvm("desktop")
    android()
    // ios()

    sourceSets {
        named("commonTest") {
            dependencies {
                implementation(kotlinTestCommon)
            }
        }
        named("androidMain") {
            dependencies {
                implementation(Libraries.SQLDelight.jvm)
                implementation(Libraries.SQLDelight.android)
            }
        }
        named("androidTest") {
            dependencies {
                implementation(kotlinTestJUnit)
            }
        }
        named("desktopMain") {
            dependencies {
                implementation(Libraries.SQLDelight.jvm)
            }
        }
        named("desktopTest") {
            dependencies {
                implementation(kotlinTestJUnit)
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
