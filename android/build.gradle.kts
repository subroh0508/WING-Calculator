import org.jetbrains.compose.compose

plugins {
    `android-application`
}

dependencies {
    implementation(project(":common:core"))
    implementation(project(":common:composeui:appframe"))
    implementation(project(":common:data:preference:model"))

    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material)

    implementation(Libraries.Jetpack.appCompat)
    implementation(Libraries.Jetpack.core)
    implementation(Libraries.Jetpack.activityCompose)

    implementation(Libraries.Koin.core)
}
