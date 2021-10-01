plugins {
    `common-multiplatform`
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                api(project(":common:data:preference:model"))
                api(project(":common:data:producer:model"))

                implementation(project(":common:utilities"))
                implementation(project(":common:data:preference:infra"))
                implementation(project(":common:data:producer:infra"))

                implementation(Libraries.Coroutines.core)

                implementation(Libraries.Koin.core)
            }
        }
    }
}
