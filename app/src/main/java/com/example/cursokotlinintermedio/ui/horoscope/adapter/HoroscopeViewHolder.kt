package com.example.cursokotlinintermedio.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.cursokotlinintermedio.databinding.ItemHoroscopeBinding
import com.example.cursokotlinintermedio.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view); //activamos el binding en el item
    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit){
        val obtenerString = binding.tvTitle.context;
        //En esta funcion se renderiza un horoscopo a la vez
        binding.ivHoroscope.setImageResource(horoscopeInfo.img);
        binding.tvTitle.text = obtenerString.getString(horoscopeInfo.name);
        binding.parent.setOnClickListener{
             //ejecutamos la funcion que le pasamos y le damos el itme por argumento despues de la animacion
            starRotationAnimation(binding.ivHoroscope, newLambda = { onItemSelected(horoscopeInfo) });
        }
    }
    private fun starRotationAnimation(view:View, newLambda:()->Unit){
        view.animate().apply {
            duration = 500;
            interpolator = LinearInterpolator();
            rotationBy(360f);
            withEndAction{newLambda()} //funcion a ejecutar
            start()
        }
    }
}