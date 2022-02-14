package reader

class TestReader(
    private val dataTest: List<String>,
    private var numberLine: Int
): PushReader {

    override fun getLine(): String {
        return dataTest[numberLine++]
    }

}