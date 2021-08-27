plugins {
    `common-multiplatform`
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                api(project(":common:data:appeal:model"))
            }
        }
    }
}
