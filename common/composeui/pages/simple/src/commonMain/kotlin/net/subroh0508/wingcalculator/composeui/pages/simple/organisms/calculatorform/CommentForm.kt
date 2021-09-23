@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.subroh0508.wingcalculator.composeui.components.molecules.textarea.SwitchableTextArea
import net.subroh0508.wingcalculator.composeui.pages.simple.dispatchers.provideInputFormDispatcher

@Composable
fun CommentForm() {
    val (uiModel, dispatch) = provideInputFormDispatcher()

    SwitchableTextArea(
        uiModel.form.comment,
        onSaveClick = { dispatch(comment = it) },
        modifier = Modifier.fillMaxWidth(),
    )
}
