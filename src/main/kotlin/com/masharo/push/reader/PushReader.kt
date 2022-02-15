package com.masharo.push.reader

//Можно получать пуши не только из консоли, главное чтобы getLine возвращал одну строку
interface PushReader {
    fun getLine(): String?
}