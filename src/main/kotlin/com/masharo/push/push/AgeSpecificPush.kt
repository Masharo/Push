package com.masharo.push.push

import com.masharo.push.Filter
import com.masharo.push.Push
import com.masharo.push.SystemState
import com.masharo.push.filter.AgeFilter
import com.masharo.push.filter.DateFilter

class AgeSpecificPush(
    override val text: String,
    override val systemState: SystemState,
    val age: Int,
    val expiryDate: Long
) : Push {

    companion object {
        const val TYPE: String = "AgeSpecificPush"
    }

    override val filters: List<Filter> = arrayListOf(
        AgeFilter(systemState, age),
        DateFilter(systemState, expiryDate)
    )

}