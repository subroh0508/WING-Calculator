package net.subroh0508.wingcalculator.composeui.pages.simple.model

import net.subroh0508.wingcalculator.appeal.model.*
import net.subroh0508.wingcalculator.preset.model.Preset

data class SimpleCalculatorUiModel(
    val form: Form = Form(),
    val query: Query = Query.Closed,
    val suggests: List<Pair<Long, Form>> = listOf(),
    val panelsCount: Int = 1,
) {
    val totalAppeals = form.let { (pIdol, sIdols, week, appealRatio, buff, appealJudge, interestRatio) ->
        TotalAppeals(
            pIdol,
            sIdols,
            week,
            Buffs(buff, buff, buff),
            appealRatio,
            appealJudge,
            interestRatio,
        )
    }

    val isResultTableHidden = panelsCount == 1 && query is Query.Opened

    fun input(
        pIdol: Idol.Produce = form.pIdol,
        sIdols: List<Idol.Support> = form.sIdols,
        week: Week = form.week,
        appealRatio: AppealRatio = form.appealRatio,
        buff: Buff = form.buff,
        appealJudge: AppealJudge = form.appealJudge,
        interestRatio: InterestRatio = form.interestRatio,
        name: String? = form.name,
        comment: String? = form.comment,
    ) = copy(
        form = Form(
            pIdol,
            sIdols,
            week,
            appealRatio,
            buff,
            appealJudge,
            interestRatio,
            id = form.id,
            name = name,
            comment = comment,
        ),
    )

    fun inputQuery(query: String?) = if (this.query is Query.Closed) this else copy(query = Query.Opened(query))

    fun update(presets: List<Preset>) = copy(suggests = presets.map { it.toSuggest() })
    fun select(preset: Preset) = select(preset.toSuggest())
    fun select(suggestion: Pair<Long, Form>) = copy(
        query = Query.Closed,
        form = suggestion.let { (id, form) ->
            val (pIdol, sIdols, _, _, _, _, _, _, name, comment) = form

            this.form.copy(pIdol = pIdol, sIdols = sIdols, id = id, name = name, comment = comment)
        },
        suggests = listOf(),
    )
    fun delete(id: Long) = copy(
        query = if (form.id == id && query is Query.Opened) Query.Opened() else query,
        form = if (form.id == id) form.copy(id = null, name = null, comment = null) else form,
        suggests = suggests.filterNot { (presetId, _) -> presetId == id },
    )

    private fun Preset.toSuggest() = id to Form(
        pIdol, sIdols, name = name, comment = comment,
    )

    data class Form(
        val pIdol: Idol.Produce = Idol.Produce(),
        val sIdols: List<Idol.Support> = listOf(Idol.Support(), Idol.Support(), Idol.Support(), Idol.Support()),
        val week: Week = Week(Week.Season.ONE),
        val appealRatio: AppealRatio = AppealRatio(2.5),
        val buff: Buff = Buff(listOf(0.0)),
        val appealJudge: AppealJudge = AppealJudge(AppealJudge.Factor.GOOD),
        val interestRatio: InterestRatio = InterestRatio(listOf(1.0)),
        val id: Long? = null,
        val name: String? = null,
        val comment: String? = null,
    )

    sealed class Query {
        abstract val text: String?

        object Closed : Query() { override val text: String? = null }
        data class Opened(override val text: String? = null) : Query()
    }
}
