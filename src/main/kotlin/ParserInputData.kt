import reader.PushReader

class ParserInputData(
    private val reader: PushReader
) {

    companion object {
        const val SYSTEM_DATA: Int = 6
        const val RESULT_ALL_NULL: String = "-1"
    }

    fun parserRun(): String {

        val systemState: SystemState = getSystemState()
        val countPush: Int = getIntValue()
        val result: StringBuilder = StringBuilder()

        for (numberPush in 1..countPush) {

            val pus = getPush(systemState).applyFilters()
            pus?.let {
                result.append(it)
            }

        }

        if (result.isEmpty()) {
            return RESULT_ALL_NULL
        }

        return result.toString()
    }

    private fun getPush(systemState: SystemState): Push {

        val countLine: Int = getIntValue()
        val data: Map<String, String> = getParams(countLine)

        if (data.containsKey("text") && data.containsKey("type")) {
            return EasyFactoryPush.createPush(data["type"]!!, systemState, data)
        }

        throw Exception("Ошибка ввода пуша. Отсутствует один из параметров text или type")
    }

    private fun getParams(countLine: Int): Map<String, String> {

        val data: MutableMap<String, String> = mutableMapOf()

        for (count in 1..countLine) {

            val line: String? = reader.getLine()

            line?.let {
                val keyAndValue = line.split(" ")
                data.put(keyAndValue[0], keyAndValue[1])
            }
        }

        return data
    }

    private fun getSystemState(): SystemState {

        val data: Map<String, String> = getParams(SYSTEM_DATA)

        //Можно проверить на null на всякий случай, но есть ли в этом смысл
        //Если какого-то параметра нет, то приложение работать не должно
        //Хотя с другой стороны должно ли оно крашиться
        return SystemState(time = data["time"]!!.toLong(),
                           age = data["age"]!!.toInt(),
                           gender = data["gender"]!![0],
                           osVersion = data["os_version"]!!.toInt(),
                           xCoord = data["x_coord"]!!.toFloat(),
                           yCoord = data["y_coord"]!!.toFloat())
    }

    private fun getIntValue(): Int {

        val countValue: String? = reader.getLine()

        countValue?.let {
            return countValue.toInt()
        }

        return 0
    }
}