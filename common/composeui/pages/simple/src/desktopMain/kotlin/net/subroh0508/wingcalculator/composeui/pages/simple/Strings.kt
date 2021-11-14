package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.runtime.Composable

@Composable
internal actual fun getString(string: Strings) = when (string) {
    Strings.CommentFormLabel -> "メモ"
    Strings.CommentFormPlaceholder -> "メモを入力"
}