import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import reader.TestReader

class Tests {

    @Test
    fun test1() {
        val testReader = TestReader(arrayListOf(
            "time 10000",
            "gender m",
            "age 21",
            "os_version 15",
            "x_coord 45.1",
            "y_coord 45.5",
            "2",
            "6",
            "type LocationAgePush",
            "text Cheap_beer_in_your_area",
            "age 18",
            "x_coord 44.1",
            "y_coord 50.0",
            "radius 10",
            "3",
            "type TechPush",
            "text Update_is_available",
            "os_version 14"
        ), 0)

        assertEquals(ParserInputData(testReader).parserRun(), "Cheap_beer_in_your_area")
    }

    @Test
    fun test2() {
        val testReader = TestReader(arrayListOf(
            "time 10000",
            "gender m",
            "age 15",
            "os_version 15",
            "x_coord 45.1",
            "y_coord 45.5",
            "1",
            "6",
            "type LocationAgePush",
            "text Cheap_beer_in_your_area",
            "age 18",
            "x_coord 44.1",
            "y_coord 50.0",
            "radius 4"
        ), 0)

        assertEquals(ParserInputData(testReader).parserRun(), "-1")
    }
}