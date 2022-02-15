package com.masharo.push.push

import com.masharo.push.Filter
import com.masharo.push.Push
import com.masharo.push.SystemState
import com.masharo.push.filter.AgeFilter
import com.masharo.push.filter.GenderFilter

class GenderAgePush(
    override val text: String,
    override val systemState: SystemState,
    val age: Int,
    val gender: Char
) : Push {

    companion object {
        const val TYPE: String = "GenderAgePush"
    }

    override val filters: List<Filter> = arrayListOf(
        AgeFilter(systemState, age),
        GenderFilter(systemState, gender)
    )

}