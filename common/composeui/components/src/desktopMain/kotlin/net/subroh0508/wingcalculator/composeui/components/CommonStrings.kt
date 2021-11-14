package net.subroh0508.wingcalculator.composeui.components

import androidx.compose.runtime.Composable

@Composable
actual fun getString(string: CommonStrings): String = when (string) {
    CommonStrings.Cancel -> "キャンセル"
    CommonStrings.Edit -> "編集"
    CommonStrings.Save -> "保存"
    CommonStrings.Close -> "閉じる"
}
