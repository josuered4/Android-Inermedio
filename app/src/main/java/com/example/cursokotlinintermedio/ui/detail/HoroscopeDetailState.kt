package com.example.cursokotlinintermedio.ui.detail

import com.example.cursokotlinintermedio.domain.model.PredictionModel

sealed class HoroscopeDetailState {
    data object Loading:HoroscopeDetailState();
    data class Error(val error: String):HoroscopeDetailState();
    data class Success(val prediction: PredictionModel):HoroscopeDetailState();
}
//Debemos definir los estado por lo que puede pasar cada pantalla
//me recuerda a block en flutter.

//los sealed class no solo peden tener objetos sino tambien clases
//con objectos creo que crea una clase generica y con clases ya nosotros
//creamos nuestas clases y objetos

//Se recomenda object, cuando no necesitamos propiedades, y solo queremos
//objetos por defecto.

//Se recomienda class cuando necesitemos campos adicionales.
