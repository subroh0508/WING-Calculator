import org.jetbrains.compose.compose

plugins {
    `android-application`
}

dependencies {
    implementation(project(":common:composeui"))

    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material)

    implementation(Libraries.Jetpack.appCompat)
    implementation(Libraries.Jetpack.core)
    implementation(Libraries.Jetpack.activityCompose)
}
