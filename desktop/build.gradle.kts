import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm(configure = KotlinJvmTarget::withJava)

    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(project(":common:core"))
                implementation(project(":common:composeui:pages:simple"))

                implementation(compose.desktop.currentOs)

                implementation(Libraries.Koin.core)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "net.subroh0508.wingcalculator.desktop.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            // @see: https://github.com/JetBrains/compose-jb/issues/381#issuecomment-779049719
            modules("java.sql")
            packageName = "jvm"
            packageVersion = "1.0.0"
        }
    }
}