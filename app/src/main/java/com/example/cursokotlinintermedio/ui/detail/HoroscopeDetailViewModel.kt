package com.example.cursokotlinintermedio.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursokotlinintermedio.domain.model.HoroscopeModel
import com.example.cursokotlinintermedio.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase):ViewModel() {

    //Inicializamos el estado del viewModel con loading
    private val _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading);
    val state: StateFlow<HoroscopeDetailState> = _state; //Creamos la propiedad que sera visible.

    lateinit var horoscope:HoroscopeModel;
    fun getHoroscope(sign: HoroscopeModel){
        horoscope = sign;
        viewModelScope.launch{ //lanzamos una corrutina a nivel de viewModel, en el hilo de UI
            //HILO UI
            _state.value = HoroscopeDetailState.Loading;
            val result = withContext(Dispatchers.IO){ //HILO SEGUNDARIO O S.O.
                    getPredictionUseCase(sign.name);
                }
            //HILO UI
            if(result!=null){
                _state.value = HoroscopeDetailState.Success(result, horoscope);
            }else{
                _state.value = HoroscopeDetailState.Error("Error de peticion");
            }
        }
    }

}