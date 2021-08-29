plugins {
    `common-multiplatform`
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:utilities"))
                implementation(project(":common:database"))

                implementation(project(":common:data:preset:infra"))
                implementation(project(":common:data:producer:infra"))

                implementation(Libraries.Koin.core)
            }
        }
    }
}

