package com.example.cursokotlinintermedio.data.network

import com.example.cursokotlinintermedio.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeApiService {
    //retrofit como tal se encarga de crear un objeto que implemente esta intefaz al igual que su clase

    @GET("/{sign}") //Anotacion de retrofit, con el end point, al igual que net6 se le puede pasar un parametro
    suspend fun getHoroscope(@Path("sign")sign:String) : PredictionResponse
    //suspend se usa func que se suspende y reanudan de forma asincronica
    //@Path es una anotacion que nos ayudara a insertar el la path indicadad un valor

}

//Para el uso de retrofit, se requiere de la creacion de una intefaz
//que defina los metodos y endPoints que se requiere consultar,
//cabe mensionar que los metodos de esta interfaz llevaran anotaciones de
//retrofit

//Nota, cada metodo retornara un dato, aca es donde decidimos crear modelos si es necesario
//o retornar un dato primitivo