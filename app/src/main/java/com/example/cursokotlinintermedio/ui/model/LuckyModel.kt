package com.example.cursokotlinintermedio.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class LuckyModel (
    @DrawableRes val img:Int,
    @StringRes val text: Int
)

//Con estos data annotations nosotros
//solo podremos almacenar referencias de
//algun recurso, solo la id del recurso por decir
//no el recurso