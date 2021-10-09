plugins {
    `common-multiplatform`
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:utilities"))
                implementation(project(":common:database"))

                implementation(project(":common:usecase:preference"))

                implementation(project(":common:data:preset:infra"))
                implementation(project(":common:data:producer:infra"))
                implementation(project(":common:data:preference:model"))
                implementation(project(":common:data:preference:infra"))

                implementation(Libraries.Koin.core)
            }
        }
    }
}

