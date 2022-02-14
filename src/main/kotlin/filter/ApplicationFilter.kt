package filter

import Filter
import SystemState
import kotlin.math.pow
import kotlin.math.sqrt

class ApplicationFilter(
    override val systemState: SystemState,
    private val xCoord: Float,
    private val yCoord: Float,
    private val radius: Int
) : Filter {

    override fun applyFilter(): Boolean {
        val result = sqrt((systemState.xCoord - xCoord).pow(2) + (systemState.yCoord - yCoord).pow(2))
        return result > radius
    }
}