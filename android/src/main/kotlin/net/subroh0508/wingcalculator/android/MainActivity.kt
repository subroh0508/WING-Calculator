package net.subroh0508.wingcalculator.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import net.subroh0508.wingcalculator.composeui.appframe.AppFrame
import net.subroh0508.wingcalculator.core.provideAppPreference

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppFrame(provideAppPreference()) }
    }
}
