package push

import Filter
import Push
import SystemState
import filter.OSVersionFilter

class TechPush(
    override val text: String,
    override val systemState: SystemState,
    val osVersion: Int
) : Push {

    companion object {
        const val TYPE: String = "TechPush"
    }

    override val filters: List<Filter> = arrayListOf(
        OSVersionFilter(systemState, osVersion)
    )

}