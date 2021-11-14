package net.subroh0508.wingcalculator.composeui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import net.subroh0508.wingcalculator.composeui.components.R

@Composable
actual fun getString(string: CommonStrings): String {
    val resources = LocalContext.current.resources

    return when (string) {
        CommonStrings.Cancel -> resources.getString(R.string.cancel)
        CommonStrings.Edit -> resources.getString(R.string.edit)
        CommonStrings.Save -> resources.getString(R.string.save)
        CommonStrings.Close -> resources.getString(R.string.close)
    }
}
