@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple.organisms

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.BuffForm
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.CommentForm
import net.subroh0508.wingcalculator.composeui.pages.simple.organisms.calculatorform.UnitStatusForm

@Composable
fun ColumnScope.CalculatorForm(modifier: Modifier) {
    UnitStatusForm(modifier)
    Divider(modifier.padding(top = 24.dp, bottom = 16.dp, start = 8.dp, end = 8.dp))
    BuffForm(modifier)
    Spacer(Modifier.height(40.dp))
    CommentForm(modifier)
}
