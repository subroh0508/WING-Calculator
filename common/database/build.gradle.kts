plugins {
    `common-multiplatform`
    id("com.squareup.sqldelight")
}

sqldelight {
    database("WingCalculatorDatabase") {
        packageName = "net.subroh0508.wingcalculator.database"
    }
}

kotlin {
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
