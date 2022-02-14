package push

import Filter
import Push
import SystemState
import filter.AgeFilter
import filter.GenderFilter

class GenderAgePush(
    override val text: String,
    override val systemState: SystemState,
    val age: Int,
    val gender: Char
) : Push {

    companion object {
        const val TYPE: String = "GenderAgePush"
    }

    override val filters: List<Filter> = arrayListOf(
        AgeFilter(systemState, age),
        GenderFilter(systemState, gender)
    )

}