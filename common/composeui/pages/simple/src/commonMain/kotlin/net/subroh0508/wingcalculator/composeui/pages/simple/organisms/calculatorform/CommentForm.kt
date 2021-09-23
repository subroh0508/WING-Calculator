@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.subroh0508.wingcalculator.composeui.components.molecules.textarea.SwitchableTextArea
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideUpdatePresetCommentDispatcher

@Composable
fun CommentForm() {
    val (uiModel, dispatch) = provideUpdatePresetCommentDispatcher()

    SwitchableTextArea(
        uiModel.form.comment,
        onSaveClick = { dispatch(it) },
        modifier = Modifier.fillMaxWidth(),
    )
}
