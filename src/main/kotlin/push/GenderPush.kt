package push

import Filter
import Push
import SystemState
import filter.GenderFilter

class GenderPush(
    override val text: String,
    override val systemState: SystemState,
    val gender: Char
) :Push {

    companion object {
        const val TYPE: String = "GenderPush"
    }

    override val filters: List<Filter> = arrayListOf(
        GenderFilter(systemState, gender)
    )

}