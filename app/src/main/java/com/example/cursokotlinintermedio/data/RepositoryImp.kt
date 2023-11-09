package com.example.cursokotlinintermedio.data

import android.util.Log
import com.example.cursokotlinintermedio.data.network.HoroscopeApiService
import com.example.cursokotlinintermedio.data.network.response.PredictionResponse
import com.example.cursokotlinintermedio.domain.Repository
import com.example.cursokotlinintermedio.domain.model.PredictionModel
import retrofit2.Retrofit
import javax.inject.Inject;

class RepositoryImp  @Inject constructor(private val apiService: HoroscopeApiService): Repository{
    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching { //es necesario a usar funciones suspend
            apiService.getHoroscope(sign);
        }.onSuccess {
            return it.toDomain();
        }.onFailure {
            Log.i("JosueRed4", "Ocurrio un erro")
        }
        return null;
    }
}

//Dado a que este es el repositorio, aca es donde tendremos que
//hacer nuestras llamadas a BBDD, retrofit"Api" u otra funete de datos