import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    js(IR) {
        browser {
            useCommonJs()
            binaries.executable()
        }
    }
    sourceSets {
        named("jsMain") {
            dependencies {
                implementation(project(":common:data"))

                implementation(compose.runtime)
                implementation(compose.web.widgets)
            }
        }
    }
}
