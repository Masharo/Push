package com.masharo.push.push

import com.masharo.push.Filter
import com.masharo.push.Push
import com.masharo.push.SystemState
import com.masharo.push.filter.OSVersionFilter

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