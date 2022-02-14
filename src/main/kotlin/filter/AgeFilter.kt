package filter

import Filter
import SystemState

class AgeFilter(
    override val systemState: SystemState,
    private val age: Int
) : Filter {

    override fun applyFilter(): Boolean =
        age <= systemState.age

}