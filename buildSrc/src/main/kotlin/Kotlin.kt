@file:Suppress("HardcodedStringLiteral")

import org.gradle.api.Project

val Project.kotlinVersion get() = version("kotlin")
val Project.kotlinStdlib get() = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"

val Project.kotlinGradlePlugin get() = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
val Project.kotlinxSerializationGradlePlugin get() = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
val Project.composeGradlePlugin get() = "org.jetbrains.compose:compose-gradle-plugin:${version("compose-jb")}"

val Project.kotlinTestCommon get() = "org.jetbrains.kotlin:kotlin-test-common:$kotlinVersion"
val Project.kotlinTestJUnit get() = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
val Project.kotlinTestJs get() = "org.jetbrains.kotlin:kotlin-test-js:$kotlinVersion"
