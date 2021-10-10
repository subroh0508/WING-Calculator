@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = primaryForDark,
    primaryVariant = primaryVariantForDark,
    secondary = secondaryForDark,
)

private val LightColorPalette = lightColors(
    primary = primaryForLight,
    primaryVariant = primaryVariantForLight,
    secondary = secondaryForLight,
    background = gray50,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) = MaterialTheme(
    colors = if (darkTheme) DarkColorPalette else LightColorPalette,
    content = content,
)
