package net.subroh0508.wingcalculator.composeui.pages.simple

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import net.subroh0508.wingcalculator.composeui.pages.simple.R

@SuppressLint("StringFormatMatches")
@Composable
internal actual fun getString(string: Strings, vararg arg: Any): String {
    val resources = LocalContext.current.resources

    return when (string) {
        Strings.CommentFormLabel -> resources.getString(R.string.simple_comment_form_label)
        Strings.CommentFormPlaceholder -> resources.getString(R.string.simple_comment_form_placeholder)
        Strings.WeekSelectorLabel -> resources.getString(R.string.simple_week_selector_label)
        Strings.WeekSelectorUnit -> resources.getString(R.string.simple_week_selector_unit, *arg)
        Strings.AppealRatioSelectorLabel -> resources.getString(R.string.simple_appeal_ratio_selector_label)
        Strings.AppealRatioSelectorUnit -> resources.getString(R.string.simple_appeal_ratio_selector_unit, *arg)
        Strings.AppealJudgeSelectorLabel -> resources.getString(R.string.simple_appeal_judge_selector_label)
        Strings.MemoryLevelSelectorLabel -> resources.getString(R.string.simple_memory_level_selector_label)
        Strings.VocalBuffRatioLabel -> resources.getString(R.string.simple_vocal_buff_ratio_label, *arg)
        Strings.DanceBuffRatioLabel -> resources.getString(R.string.simple_dance_buff_ratio_label, *arg)
        Strings.VisualBuffRatioLabel -> resources.getString(R.string.simple_visual_buff_ratio_label, *arg)
        Strings.BuffRatioHelperText -> resources.getString(R.string.simple_buff_ratio_helper_text)
        Strings.InterestRatioLabel -> resources.getString(R.string.simple_interest_ratio_label, *arg)
        Strings.InterestRatioHelperText -> resources.getString(R.string.simple_interest_ratio_helper_text)
        Strings.ProduceIdolStatusFormLabel -> resources.getString(R.string.simple_produce_idol_status_form_label)
        Strings.SupportIdolStatusFormLabel -> resources.getString(R.string.simple_support_idol_status_form_label, *arg)
        Strings.IdolVocalStatusFormLabel -> resources.getString(R.string.simple_idol_vocal_status_form_label)
        Strings.IdolDanceStatusFormLabel -> resources.getString(R.string.simple_idol_dance_status_form_label)
        Strings.IdolVisualStatusFormLabel -> resources.getString(R.string.simple_idol_visual_status_form_label)
        Strings.IdolMentalStatusFormLabel -> resources.getString(R.string.simple_idol_mental_status_form_label)
        Strings.BackdropFrontLayerHeaderTitle -> resources.getString(R.string.simple_backdrop_front_layer_header_title)
        Strings.TableSwitcherAppealLabel -> resources.getString(R.string.simple_table_switcher_appeal_label)
        Strings.TableSwitcherAppealLabelVocal -> resources.getString(R.string.simple_table_switcher_appeal_label_vocal)
        Strings.TableSwitcherAppealLabelDance -> resources.getString(R.string.simple_table_switcher_appeal_label_dance)
        Strings.TableSwitcherAppealLabelVisual -> resources.getString(R.string.simple_table_switcher_appeal_label_visual)
        Strings.TableSwitcherAppealLabelMemory -> resources.getString(R.string.simple_table_switcher_appeal_label_memory)
        Strings.TableSwitcherJudgeLabel -> resources.getString(R.string.simple_table_switcher_judge_label)
        Strings.TableSwitcherJudgeLabelVocal -> resources.getString(R.string.simple_table_switcher_judge_label_vocal)
        Strings.TableSwitcherJudgeLabelDance -> resources.getString(R.string.simple_table_switcher_judge_label_dance)
        Strings.TableSwitcherJudgeLabelVisual -> resources.getString(R.string.simple_table_switcher_judge_label_visual)
        Strings.TableColumnLabelProduce -> resources.getString(R.string.simple_table_column_label_produce)
        Strings.TableColumnLabelSupport1 -> resources.getString(R.string.simple_table_column_label_support_1)
        Strings.TableColumnLabelSupport2 -> resources.getString(R.string.simple_table_column_label_support_2)
        Strings.TableColumnLabelSupport3 -> resources.getString(R.string.simple_table_column_label_support_3)
        Strings.TableColumnLabelSupport4 -> resources.getString(R.string.simple_table_column_label_support_4)
        Strings.PresetSaveDialogTitle -> resources.getString(R.string.simple_preset_save_dialog_title)
        Strings.PresetSaveMenuCreate -> resources.getString(R.string.simple_preset_save_menu_create)
        Strings.PresetSaveMenuUpdate -> resources.getString(R.string.simple_preset_save_menu_update)
        Strings.PresetSearchBarText -> resources.getString(R.string.simple_preset_search_bar_text, *arg)
        Strings.PresetSearchBarPlaceholder -> resources.getString(R.string.simple_preset_search_bar_placeholder)
        Strings.MessageSearchPresetSuggestsEmpty -> resources.getString(R.string.simple_message_search_preset_suggests_empty)
    }
}
