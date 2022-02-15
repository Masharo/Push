package com.masharo.push.reader

class ConsoleReader: PushReader {

    override fun getLine(): String? = readLine()

}