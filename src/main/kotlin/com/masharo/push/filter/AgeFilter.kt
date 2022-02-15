package com.masharo.push.filter

import com.masharo.push.Filter
import com.masharo.push.SystemState

class AgeFilter(
    override val systemState: SystemState,
    private val age: Int
) : Filter {

    override fun applyFilter(): Boolean =
        age > systemState.age

}