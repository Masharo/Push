package com.masharo.push

interface Filter {
    val systemState: SystemState

    //Должен вернуть true если пуш должен быть отфильтрован
    //т.е. не показан
    fun applyFilter(): Boolean
}