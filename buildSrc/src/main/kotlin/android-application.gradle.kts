plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose")
}

androidBaseExt()
android {
    defaultConfig {
        applicationId = Android.applicationId
        versionCode = Android.versionCode
        versionName = Android.versionName
    }

    packagingOptions {
        resources {
            excludes.add("META-INF/*")
        }
    }
}

