package com.masharo.push.push

import com.masharo.push.Filter
import com.masharo.push.Push
import com.masharo.push.SystemState
import com.masharo.push.filter.GenderFilter

class GenderPush(
    override val text: String,
    override val systemState: SystemState,
    val gender: Char
) : Push {

    companion object {
        const val TYPE: String = "GenderPush"
    }

    override val filters: List<Filter> = arrayListOf(
        GenderFilter(systemState, gender)
    )

}