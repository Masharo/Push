package com.masharo.push.filter

import com.masharo.push.Filter
import com.masharo.push.SystemState

class DateFilter(
    override val systemState: SystemState,
    private val expiryDate: Long
) : Filter {

    override fun applyFilter(): Boolean =
        expiryDate < systemState.time

}