package com.example.cursokotlinintermedio.domain

import com.example.cursokotlinintermedio.data.network.response.PredictionResponse
import com.example.cursokotlinintermedio.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign:String): PredictionModel?;
}

//Creamos una interfaz que defina las acciones que el repositorio
//debe realizar, esto para hacer una inversion de dependencias