plugins {
    `compose-multiplatform`
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:composeui:components"))
                implementation(project(":common:usecase:preference"))

                implementation(project(":common:composeui:pages:simple"))
            }
        }
    }
}
