package filter

import Filter
import SystemState

class GenderFilter(
    override val systemState: SystemState,
    private val gender: Char
) : Filter {

    override fun applyFilter(): Boolean =
        gender != systemState.gender

}