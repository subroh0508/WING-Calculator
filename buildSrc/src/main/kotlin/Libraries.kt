@Suppress("HardcodedStringLiteral", "unused")

object Libraries {
    object Coroutines {
        private const val version = "1.5.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    }

    object Datetime {
        private const val version = "0.2.1"
        const val common = "org.jetbrains.kotlinx:kotlinx-datetime:$version"
    }

    object Jetpack {
        private const val appCompatVersion = "1.3.0"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

        private const val coreVersion = "1.6.0"
        const val core = "androidx.core:core-ktx:$coreVersion"

        private const val activityVersion = "1.3.0"
        const val activityCompose = "androidx.activity:activity-compose:$activityVersion"
    }

    object SQLDelight {
        private const val version = "1.5.1"
        const val plugin = "com.squareup.sqldelight:gradle-plugin:$version"

        const val jvm = "com.squareup.sqldelight:sqlite-driver:$version"
        const val android = "com.squareup.sqldelight:android-driver:$version"
        const val ios = "com.squareup.sqldelight:ios-driver:$version"

        const val coroutines = "com.squareup.sqldelight:coroutines-extensions:$version"
    }
}