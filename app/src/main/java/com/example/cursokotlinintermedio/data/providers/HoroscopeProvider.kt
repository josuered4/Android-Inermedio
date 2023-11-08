package com.example.cursokotlinintermedio.data.providers

import com.example.cursokotlinintermedio.domain.model.HoroscopeInfo
import com.example.cursokotlinintermedio.domain.model.HoroscopeInfo.*
import javax.inject.Inject

class HoroscopeProvider @Inject constructor() {
    //preparamos la clase para ser inyectada
    fun getHoroscopes():List<HoroscopeInfo>{
        return listOf(
            Aries,Tauro,Gemini,
            Aquarius,Cancer,Capricorn,
            Leo,Libra,Pices,
            Sagittarius, Scorpio, Virgo
        )
    }
}