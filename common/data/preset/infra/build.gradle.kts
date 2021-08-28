plugins {
    `common-multiplatform`
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:utilities"))
                implementation(project(":common:database"))
                implementation(project(":common:data:preset:model"))

                implementation(Libraries.Coroutines.core)
                implementation(Libraries.Datetime.common)
                implementation(Libraries.SQLDelight.coroutines)
            }
        }
    }
}