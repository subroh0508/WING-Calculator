plugins {
    `compose-multiplatform`
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:data"))
                implementation(project(":common:composeui:components"))
            }
        }
    }
}
