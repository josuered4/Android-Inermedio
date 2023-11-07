package com.example.cursokotlinintermedio.ui.horoscope.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cursokotlinintermedio.databinding.ItemHoroscopeBinding
import com.example.cursokotlinintermedio.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view); //activamos el binding en el item
    fun render(horoscopeInfo: HoroscopeInfo){
        //En esta funcion se renderiza un horoscopo a la vez
        binding.ivHoroscope.setImageResource(horoscopeInfo.img);
        binding.tvTitle.text = binding.tvTitle.context.getString(horoscopeInfo.name);
    }
}