package push

import Filter
import Push
import SystemState
import filter.AgeFilter
import filter.ApplicationFilter

class LocationAgePush(
    override val text: String,
    override val systemState: SystemState,
    val xCoord: Float,
    val yCoord: Float,
    val radius: Int,
    val age: Int
) : Push {

    companion object {
        const val TYPE: String = "LocationAgePush"
    }

    override val filters: List<Filter> = arrayListOf(
        ApplicationFilter(systemState, xCoord, yCoord, radius),
        AgeFilter(systemState, age)
    )

}