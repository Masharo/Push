package push

import Filter
import Push
import SystemState
import filter.ApplicationFilter

class LocationPush(
    override val type: String,
    override val text: String,
    override val systemState: SystemState,
    val x_coord: Float,
    val y_coord: Float,
    val radius: Int,
    val expiry_date: Long
) : Push {

    override val filters: List<Filter> = arrayListOf(
        ApplicationFilter(systemState, x_coord, y_coord, radius)
    )

    override val params: Set<String>
        get() = setOf("x_coord", "y_coord", "radius", "expiry_date")

}