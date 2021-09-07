@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.atoms.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

private val FOOTER_HEIGHT = 120.dp

@Composable
fun Footer(
    isVisibleText: Boolean = false,
    text: @Composable (() -> Unit)? = null,
) = if (isVisibleText && text != null)
        text()
    else
        Spacer(modifier = Modifier.height(FOOTER_HEIGHT))

@Composable
fun Footer(
    isVisibleText: Boolean,
    text: String,
) = Footer(isVisibleText) {
    Text(
        text,
        style = MaterialTheme.typography.subtitle1,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp),
    )
}
