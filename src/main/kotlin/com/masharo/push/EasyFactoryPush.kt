package com.masharo.push

import com.masharo.push.push.*
import java.lang.Exception

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

            else -> throw Exception()
        }

}