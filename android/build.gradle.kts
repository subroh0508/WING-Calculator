plugins {
    `android-application`
}

dependencies {
    implementation(project(":common"))

    implementation(Libraries.Jetpack.appCompat)
    implementation(Libraries.Jetpack.core)
    implementation(Libraries.Jetpack.activityCompose)
}
