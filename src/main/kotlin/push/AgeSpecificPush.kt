package push

import Filter
import Push
import SystemState
import filter.AgeFilter
import filter.DateFilter

class AgeSpecificPush(
    override val text: String,
    override val systemState: SystemState,
    val age: Int,
    val expiryDate: Long
) : Push {

    companion object {
        const val TYPE: String = "AgeSpecificPush"
    }

    override val filters: List<Filter> = arrayListOf(
        AgeFilter(systemState, age),
        DateFilter(systemState, expiryDate)
    )

}