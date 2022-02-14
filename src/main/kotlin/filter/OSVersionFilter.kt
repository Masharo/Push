package filter

import Filter
import SystemState


class OSVersionFilter(
    override val systemState: SystemState,
    private val osVersion: Int
) : Filter {

    override fun applyFilter(): Boolean =
        osVersion <= systemState.osVersion

}