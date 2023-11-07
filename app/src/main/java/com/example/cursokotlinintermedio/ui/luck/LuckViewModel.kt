package com.example.cursokotlinintermedio.ui.luck

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel //indica que es un viewModel inyectable
class LuckViewModel @Inject constructor() : ViewModel(){
    //con @Inject constructor indica la creacion de un objeto cuando se necesite
}