@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

interface SwitcherLabel {
    val text: String

    operator fun component1() = text

    fun next(): SwitcherLabel
    fun previous(): SwitcherLabel
}

inline fun <reified E: Enum<E>> E.nextEnum() = enumValues<E>().let { it[(ordinal + 1) % it.size] }
inline fun <reified E: Enum<E>> E.previousEnum() = enumValues<E>().let { it[(it.size + (ordinal - 1)) % it.size] }

enum class SwitcherOrientation(
    val startIcon: ImageVector,
    val endIcon: ImageVector,
    val startDescription: String = "back",
    val endDescription: String = "forward",
) {
    VERTICAL(Icons.Default.KeyboardArrowUp, Icons.Default.KeyboardArrowDown),
    HORIZONTAL(Icons.Default.KeyboardArrowLeft, Icons.Default.KeyboardArrowRight);
}

@Composable
fun <T: SwitcherLabel> ColumnScope.Switcher(
    type: T,
    orientation: SwitcherOrientation,
    onClickBack: (() -> Unit)?,
    onClickForward: (() -> Unit)?,
) = Row(
    modifier = Modifier.padding(vertical = 8.dp)
        .align(Alignment.CenterHorizontally),
) {
    if (onClickBack != null)
        IconButton(onClickBack) {
            Icon(
                orientation.startIcon,
                contentDescription = orientation.startDescription,
                modifier = Modifier.size(24.dp),
            )
        }
    else
        Spacer(Modifier.width(48.dp))
    Text(
        type.text,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(horizontal = 16.dp)
            .align(Alignment.CenterVertically),
    )
    if (onClickForward != null)
        IconButton(onClickForward) {
            Icon(
                orientation.endIcon,
                contentDescription = orientation.endDescription,
                modifier = Modifier.size(24.dp),
            )
        }
    else
        Spacer(Modifier.width(48.dp))
}

