package filter

import Filter
import SystemState

class DateFilter(
    override val systemState: SystemState,
    private val expiryDate: Long
) : Filter {

    override fun applyFilter(): Boolean =
        expiryDate >= systemState.time

}