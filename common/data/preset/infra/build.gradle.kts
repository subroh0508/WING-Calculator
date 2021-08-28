plugins {
    `infra-multiplatform`
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:data:preset:model"))

                implementation(Libraries.Datetime.common)
            }
        }
    }
}
