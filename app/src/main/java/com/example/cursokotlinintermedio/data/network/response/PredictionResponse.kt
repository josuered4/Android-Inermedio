package com.example.cursokotlinintermedio.data.network.response

import com.example.cursokotlinintermedio.domain.model.PredictionModel
import com.google.gson.annotations.SerializedName

data class PredictionResponse(
    @SerializedName("date") val date:String,
    @SerializedName("horoscope") val horoscope:String,
    @SerializedName("sign") val sign:String,
    //@SerializedName("sign") nos ayuda a buscar dentro de un json un valor
    //para asignarlo en una propiedad
) {
    fun toDomain():PredictionModel{
        return PredictionModel(horoscope, sign);
    }
};
//Esta data class es una nueva forma de crear modelos en Kotlin
//pero en este caso no solo se usara kotlin sino retrofit gson
//usando sus anotaciones podremos serializar los nombres, haciendo
//que estos modelos sean especiales solo para la capa de data