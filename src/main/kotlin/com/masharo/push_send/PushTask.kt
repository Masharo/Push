import kotlin.math.pow
import kotlin.math.sqrt


fun main(args: Array<String>) {
    println(ParserInputData(ConsoleReader()).parserRun())
}



//Общая номенклатура, после 5 проваленых тестов я осознал ее надобность
object GeneralNomenclature {

    const val AGE = "age"
    const val TIME = "time"
    const val GENDER = "gender"
    const val OS_VERSION = "os_version"
    const val X_COORD = "x_coord"
    const val Y_COORD = "y_coord"
    const val EXPIRY_DATE = "expiry_date"
    const val RADIUS = "radius"
    const val TEXT = "text"

}



data class SystemState(
    val time: Long,
    val age: Int,
    val gender: Char,
    val osVersion: Int,
    val xCoord: Float,
    val yCoord: Float
)



interface Push {
    val text: String
    val systemState: SystemState

    //Набор фильтров
    val filters: List<Filter>

    //Применяем фильтры
    fun applyFilters(): String? {
        filters.forEach { filter -> if (filter.applyFilter()) return null }
        return text
    }
}



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
                if (result.isNotEmpty()) {
                    result.append("\n")
                }
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
            try {
                return EasyFactoryPush.createPush(data["type"]!!, systemState, data)
            } catch (ex: Exception) {
                throw Exception("EasyFactoryPush: ошибка получения параметров пушей")
            }

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

        try {
            return SystemState(time = data[GeneralNomenclature.TIME]!!.toLong(),
                               age = data[GeneralNomenclature.AGE]!!.toInt(),
                               gender = data[GeneralNomenclature.GENDER]!![0],
                               osVersion = data[GeneralNomenclature.OS_VERSION]!!.toInt(),
                               xCoord = data[GeneralNomenclature.X_COORD]!!.toFloat(),
                               yCoord = data[GeneralNomenclature.Y_COORD]!!.toFloat())
        } catch (ex: Exception) {
            throw Exception("ParserInputData: ошибка получения данных системы")
        }

    }

    private fun getIntValue(): Int {

        val countValue: String? = reader.getLine()

        countValue?.let {
            return countValue.toInt()
        }

        return 0
    }
}



interface Filter {
    val systemState: SystemState

    //Должен вернуть true если пуш должен быть отфильтрован
    //т.е. не показан
    fun applyFilter(): Boolean
}



//Регаем пуши чтобы потом создать
object EasyFactoryPush {

    fun createPush(pushString: String, systemState: SystemState, data: Map<String, String>): Push =

        when (pushString) {

            LocationPush.TYPE -> LocationPush(
                                    systemState = systemState,
                                    text = data[GeneralNomenclature.TEXT]!!,
                                    xCoord = data[GeneralNomenclature.X_COORD]!!.toFloat(),
                                    yCoord = data[GeneralNomenclature.Y_COORD]!!.toFloat(),
                                    radius = data[GeneralNomenclature.RADIUS]!!.toInt(),
                                    expiryDate = data[GeneralNomenclature.EXPIRY_DATE]!!.toLong())

            AgeSpecificPush.TYPE -> AgeSpecificPush(
                                    systemState = systemState,
                                    text = data[GeneralNomenclature.TEXT]!!,
                                    age = data[GeneralNomenclature.AGE]!!.toInt(),
                                    expiryDate = data[GeneralNomenclature.EXPIRY_DATE]!!.toLong())

            TechPush.TYPE -> TechPush(
                                    systemState = systemState,
                                    text = data[GeneralNomenclature.TEXT]!!,
                                    osVersion = data[GeneralNomenclature.OS_VERSION]!!.toInt())

            LocationAgePush.TYPE -> LocationAgePush(
                                    systemState = systemState,
                                    text = data[GeneralNomenclature.TEXT]!!,
                                    xCoord = data[GeneralNomenclature.X_COORD]!!.toFloat(),
                                    yCoord = data[GeneralNomenclature.Y_COORD]!!.toFloat(),
                                    radius = data[GeneralNomenclature.RADIUS]!!.toInt(),
                                    age = data[GeneralNomenclature.AGE]!!.toInt())

            GenderAgePush.TYPE -> GenderAgePush(
                                    systemState = systemState,
                                    text = data[GeneralNomenclature.TEXT]!!,
                                    age = data[GeneralNomenclature.AGE]!!.toInt(),
                                    gender = data[GeneralNomenclature.GENDER]!![0])

            GenderPush.TYPE -> GenderPush(
                                    systemState = systemState,
                                    text = data[GeneralNomenclature.TEXT]!!,
                                    gender = data[GeneralNomenclature.GENDER]!![0])

            else -> throw java.lang.Exception()
        }

}



class AgeFilter(
    override val systemState: SystemState,
    private val age: Int
) : Filter {

    override fun applyFilter(): Boolean =
        age > systemState.age

}



class ApplicationFilter(
    override val systemState: SystemState,
    private val xCoord: Float,
    private val yCoord: Float,
    private val radius: Int
) : Filter {

    override fun applyFilter(): Boolean {
        val result = sqrt((systemState.xCoord - xCoord).pow(2) + (systemState.yCoord - yCoord).pow(2))
        return result > radius
    }
}



class DateFilter(
    override val systemState: SystemState,
    private val expiryDate: Long
) : Filter {

    override fun applyFilter(): Boolean =
        expiryDate < systemState.time

}



class GenderFilter(
    override val systemState: SystemState,
    private val gender: Char
) : Filter {

    override fun applyFilter(): Boolean =
        gender != systemState.gender

}



class OSVersionFilter(
    override val systemState: SystemState,
    private val osVersion: Int
) : Filter {

    override fun applyFilter(): Boolean =
        osVersion < systemState.osVersion

}



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



class GenderAgePush(
    override val text: String,
    override val systemState: SystemState,
    val age: Int,
    val gender: Char
) : Push {

    companion object {
        const val TYPE: String = "GenderAgePush"
    }

    override val filters: List<Filter> = arrayListOf(
        AgeFilter(systemState, age),
        GenderFilter(systemState, gender)
    )

}



class GenderPush(
    override val text: String,
    override val systemState: SystemState,
    val gender: Char
) :Push {

    companion object {
        const val TYPE: String = "GenderPush"
    }

    override val filters: List<Filter> = arrayListOf(
        GenderFilter(systemState, gender)
    )

}



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



//Можно получать пуши не только из консоли, главное чтобы getLine возвращал одну строку
interface PushReader {
    fun getLine(): String?
}



class ConsoleReader: PushReader {

    override fun getLine(): String? = readLine()

}