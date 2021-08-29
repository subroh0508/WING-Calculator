plugins {
    `common-multiplatform`
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                api(project(":common:data:preset:model"))

                implementation(project(":common:utilities"))

                implementation(project(":common:data:preset:infra"))
                implementation(project(":common:data:producer:infra"))

                implementation(Libraries.Coroutines.core)

                implementation(Libraries.Koin.core)
            }
        }
    }
}
