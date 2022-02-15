package com.masharo.push.filter

import com.masharo.push.Filter
import com.masharo.push.SystemState

class GenderFilter(
    override val systemState: SystemState,
    private val gender: Char
) : Filter {

    override fun applyFilter(): Boolean =
        gender != systemState.gender

}