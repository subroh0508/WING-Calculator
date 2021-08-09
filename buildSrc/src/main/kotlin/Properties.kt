@file:Suppress("HardcodedStringLiteral")

import org.gradle.api.Project
import org.gradle.api.Task

fun Project.version(target: String) = prop("$target.version")

internal fun Project.prop(propertyName: String) = property(propertyName) as String
internal fun Task.prop(propertyName: String) = project.prop(propertyName)
