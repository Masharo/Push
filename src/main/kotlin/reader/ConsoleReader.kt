package reader

class ConsoleReader: PushReader {

    override fun getLine(): String? = readLine()

}