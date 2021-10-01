plugins {
    `infra-multiplatform`
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:data:preference:model"))
            }
        }
    }
}
