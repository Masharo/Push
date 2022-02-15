package com.masharo.push

import com.masharo.push.reader.ConsoleReader

fun main(args: Array<String>) {
    println(ParserInputData(ConsoleReader()).parserRun())
}