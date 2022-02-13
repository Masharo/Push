package filter

import Filter
import SystemState
import kotlin.math.pow
import kotlin.math.sqrt

class ApplicationFilter(
    override val systemState: SystemState,
    val x_coord: Float,
    val y_coord: Float,
    val radius: Int
) : Filter {

    override fun apply(): Boolean {
        val result = sqrt((systemState.x_coord - x_coord).pow(2) + (systemState.y_coord - y_coord).pow(2))
        return result <= radius
    }
}