package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import net.subroh0508.wingcalculator.composeui.pages.simple.R

@Composable
internal actual fun getString(string: Strings): String {
    val resources = LocalContext.current.resources

    return when (string) {
        Strings.CommentFormLabel -> resources.getString(R.string.simple_comment_form_label)
        Strings.CommentFormPlaceholder -> resources.getString(R.string.simple_comment_form_placeholder)
    }
}
