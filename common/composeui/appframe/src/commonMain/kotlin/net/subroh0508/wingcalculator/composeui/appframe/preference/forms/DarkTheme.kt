@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.appframe.preference.forms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.appframe.AppPreferenceDispatcherContext
import net.subroh0508.wingcalculator.composeui.appframe.Strings
import net.subroh0508.wingcalculator.composeui.appframe.getString
import net.subroh0508.wingcalculator.preference.model.AppPreference

@Composable
fun ColumnScope.DarkTheme(preference: AppPreference) {
    val dispatcher = AppPreferenceDispatcherContext.current

    Text(
        getString(Strings.PreferenceColorThemeLabel),
        style = MaterialTheme.typography.subtitle2,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)
    )

    AppPreference.Theme.values().forEach { theme ->
        ListItem(
            modifier = Modifier.clickable {
                dispatcher(preference.copy(theme = theme))
            }
        ) {
            Row(Modifier.fillMaxWidth()) {
                Text(theme.label, modifier = Modifier.weight(1F))
                RadioButton(preference.theme == theme, onClick = {
                    dispatcher(preference.copy(theme = theme))
                })
            }
        }
    }
}

private val AppPreference.Theme.label @Composable get() = when (this) {
    AppPreference.Theme.SYSTEM -> getString(Strings.PreferenceColorThemeSystem)
    AppPreference.Theme.LIGHT -> getString(Strings.PreferenceColorThemeLight)
    AppPreference.Theme.DARK -> getString(Strings.PreferenceColorThemeDark)
}
