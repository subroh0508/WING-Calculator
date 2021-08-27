plugins {
    `compose-multiplatform`
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:data:appeal:model"))
                implementation(project(":common:composeui:components"))
            }
        }
    }
}
