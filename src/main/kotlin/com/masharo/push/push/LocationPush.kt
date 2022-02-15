package com.masharo.push.push

import com.masharo.push.Filter
import com.masharo.push.Push
import com.masharo.push.SystemState
import com.masharo.push.filter.ApplicationFilter
import com.masharo.push.filter.DateFilter

class LocationPush(
    override val text: String,
    override val systemState: SystemState,
    val xCoord: Float,
    val yCoord: Float,
    val radius: Int,
    val expiryDate: Long
) : Push {

    companion object {
        const val TYPE: String = "LocationPush"
    }

    override val filters: List<Filter> = arrayListOf(
        ApplicationFilter(systemState, xCoord, yCoord, radius),
        DateFilter(systemState, expiryDate)
    )
}