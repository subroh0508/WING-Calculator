package net.subroh0508.wingcalculator.android

import net.subroh0508.wingcalculator.composeui.pages.simple.SimpleCalculatorPage
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SimpleCalculatorPage() }
    }
}
