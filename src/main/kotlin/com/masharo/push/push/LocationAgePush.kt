package com.masharo.push.push

import com.masharo.push.Filter
import com.masharo.push.Push
import com.masharo.push.SystemState
import com.masharo.push.filter.AgeFilter
import com.masharo.push.filter.ApplicationFilter

class LocationAgePush(
    override val text: String,
    override val systemState: SystemState,
    val xCoord: Float,
    val yCoord: Float,
    val radius: Int,
    val age: Int
) : Push {

    companion object {
        const val TYPE: String = "LocationAgePush"
    }

    override val filters: List<Filter> = arrayListOf(
        ApplicationFilter(systemState, xCoord, yCoord, radius),
        AgeFilter(systemState, age)
    )

}