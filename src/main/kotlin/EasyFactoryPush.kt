import push.*
import java.lang.Exception

//Регаем пуши чтобы потом создать
object EasyFactoryPush {

    fun createPush(pushString: String, systemState: SystemState, data: Map<String, String>): Push =

        when (pushString) {

            LocationPush.TYPE -> LocationPush(
                                            systemState = systemState,
                                            text = data["text"]!!,
                                            xCoord = data["x_coord"]!!.toFloat(),
                                            yCoord = data["y_coord"]!!.toFloat(),
                                            radius = data["radius"]!!.toInt(),
                                            expiryDate = data["expiry_date"]!!.toLong())

            AgeSpecificPush.TYPE -> AgeSpecificPush(
                                            systemState = systemState,
                                            text = data["text"]!!,
                                            age = data["age"]!!.toInt(),
                                            expiryDate = data["expiryDate"]!!.toLong())

            TechPush.TYPE -> TechPush(
                                            systemState = systemState,
                                            text = data["text"]!!,
                                            osVersion = data["os_version"]!!.toInt())

            LocationAgePush.TYPE -> LocationAgePush(
                                            systemState = systemState,
                                            text = data["text"]!!,
                                            xCoord = data["x_coord"]!!.toFloat(),
                                            yCoord = data["y_coord"]!!.toFloat(),
                                            radius = data["radius"]!!.toInt(),
                                            age = data["age"]!!.toInt())

            GenderAgePush.TYPE -> GenderAgePush(
                                            systemState = systemState,
                                            text = data["text"]!!,
                                            age = data["age"]!!.toInt(),
                                            gender = data["gender"]!![0])

            GenderPush.TYPE -> GenderPush(
                                            systemState = systemState,
                                            text = data["text"]!!,
                                            gender = data["gender"]!![0])

            else -> throw Exception("Пуш отсутсвует в EasyFactoryPush")
        }

}