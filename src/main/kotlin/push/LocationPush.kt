package push

import Filter
import Push
import SystemState
import filter.ApplicationFilter
import filter.DateFilter

class LocationPush(
    override val text: String,
    override val systemState: SystemState,
    val xCoord: Float,
    val yCoord: Float,
    val radius: Int,
    val expiryDate: Long
) : Push {

    companion object {
        const val TYPE: String = "LocationPush"
    }

    override val filters: List<Filter> = arrayListOf(
        ApplicationFilter(systemState, xCoord, yCoord, radius),
        DateFilter(systemState, expiryDate)
    )
}