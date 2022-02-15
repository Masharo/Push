package com.masharo.push.filter

import com.masharo.push.Filter
import com.masharo.push.SystemState


class OSVersionFilter(
    override val systemState: SystemState,
    private val osVersion: Int
) : Filter {

    override fun applyFilter(): Boolean =
        osVersion < systemState.osVersion

}