package com.example.cursokotlinintermedio.domain.usecase

import com.example.cursokotlinintermedio.domain.Repository
import com.example.cursokotlinintermedio.domain.model.PredictionModel
import javax.inject.Inject


class GetPredictionUseCase @Inject constructor(private val repository: Repository) {

    // La función 'invoke' permite que la clase se comporte como una función.
    suspend operator fun invoke(sign: String): PredictionModel? {
        // Dentro de esta función, se realiza una operación específica.
        return repository.getPrediction(sign)
    }
}

