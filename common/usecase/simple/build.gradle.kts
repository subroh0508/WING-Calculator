plugins {
    `common-multiplatform`
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                api(project(":common:data:appeal:model"))
                api(project(":common:data:preset:model"))
                api(project(":common:data:producer:model"))
                api(project(":common:data:preference:model"))

                implementation(project(":common:utilities"))
                api(project(":common:data:appeal:infra"))
                implementation(project(":common:data:preset:infra"))
                implementation(project(":common:data:producer:infra"))

                implementation(Libraries.Coroutines.core)

                implementation(Libraries.Koin.core)
            }
        }
    }
}
