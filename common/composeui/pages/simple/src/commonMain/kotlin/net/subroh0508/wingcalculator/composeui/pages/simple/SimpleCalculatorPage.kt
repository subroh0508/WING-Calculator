@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.pages.simple

import androidx.compose.runtime.*
import net.subroh0508.wingcalculator.composeui.components.di.KoinComponentContainer
import net.subroh0508.wingcalculator.composeui.components.di.UiModelDispatcher
import net.subroh0508.wingcalculator.composeui.components.di.UiModelProvider
import net.subroh0508.wingcalculator.composeui.components.di.emptyUiModelDispatcher
import net.subroh0508.wingcalculator.composeui.components.themes.AppTheme
import net.subroh0508.wingcalculator.composeui.pages.simple.model.SimpleCalculatorUiModel
import net.subroh0508.wingcalculator.composeui.pages.simple.templates.SimpleCalculatorDrawer
import net.subroh0508.wingcalculator.usecase.simple.di.SimpleCalculatorDomainModule

typealias SimpleCalculatorDispatcher = UiModelDispatcher<SimpleCalculatorUiModel>
typealias SimpleCalculatorProvider = UiModelProvider<SimpleCalculatorUiModel>

val SimpleCalculatorDispatcherContext = compositionLocalOf<SimpleCalculatorDispatcher>(
    defaultFactory = { ::emptyUiModelDispatcher },
)
val SimpleCalculatorProviderContext = compositionLocalOf(
    defaultFactory = { SimpleCalculatorProvider(null, SimpleCalculatorUiModel()) },
)

@Composable
fun SimpleCalculatorPage() {
    // TODO 値の永続化タイミングをプリセット名変更 → リアルタイムに変更
    // TODO 保存されたプリセットが残り1件の時は作成できないように or 名前空欄のプリセットが検索から除外されるように
    AppTheme {
        KoinComponentContainer(
            SimpleCalculatorUiModel(),
            SimpleCalculatorDomainModule,
            SimpleCalculatorDispatcherContext,
            SimpleCalculatorProviderContext,
        ) { SimpleCalculatorDrawer() }
    }
}
