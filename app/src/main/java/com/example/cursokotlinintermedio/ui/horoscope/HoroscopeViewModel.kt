package com.example.cursokotlinintermedio.ui.horoscope

import androidx.lifecycle.ViewModel
import com.example.cursokotlinintermedio.domain.model.HoroscopeInfo
import com.example.cursokotlinintermedio.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel //indica que es un elemento viewModel inyectable
class HoroscopeViewModel @Inject constructor() :ViewModel() {

    //inicializamos de forma vacia el flow que almacenara los datos que representan
    //el estado, en otras palabras los datos recibidos de alguna fuente y que se
    //deberan mandar al view, pero solo podra ser leida y modificada desde el viewModel
    private var _horoscopos = MutableStateFlow<List<HoroscopeInfo>>(emptyList());

    val horoscopos: StateFlow<List<HoroscopeInfo>> = _horoscopos;
    //Creamos una propiedad para mandar los datos del estado a la vista, aunque modifiquemos
    //estos datos, el estado principal no se vera comprometido, es como una propiedad get.

    init {
        //metodo especial de los viewModel, es como el onCreate de Activities
        //se ejecuta al inicializar un objeto
        _horoscopos.value = listOf( //en este caso inicializamos el estado con 3 objetos
            Aries,Tauro,Gemini,
            Aquarius,Cancer,Capricorn,
            Leo,Libra,Pices,
            Sagittarius, Scorpio, Virgo
        );
    }

}

//para la comunicacion contante entre el viewModel y la view
//se usaba liveData pero ahora se implementan los stateFlow
//que son como el liveData pero mas potente, esto para mantener
//siempre comunicados el viewModel y la view en este caso por medio
//de los stateDlow