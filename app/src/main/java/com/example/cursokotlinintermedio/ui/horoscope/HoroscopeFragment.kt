package com.example.cursokotlinintermedio.ui.horoscope

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cursokotlinintermedio.R
import com.example.cursokotlinintermedio.databinding.FragmentHoroscopeBinding

class HoroscopeFragment : Fragment() {

    private var _binding:FragmentHoroscopeBinding? = null;
    private val binding get() = _binding!!;

    //A diferencia de los activities, esta funcion crea el Fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater,container,false);
        return binding.root;
    }



}