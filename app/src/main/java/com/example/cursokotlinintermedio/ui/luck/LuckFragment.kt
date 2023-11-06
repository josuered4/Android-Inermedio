package com.example.cursokotlinintermedio.ui.luck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cursokotlinintermedio.R
import com.example.cursokotlinintermedio.databinding.FragmentHoroscopeBinding
import com.example.cursokotlinintermedio.databinding.FragmentLuckBinding

class LuckFragment : Fragment() {
    private var _binding: FragmentLuckBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLuckBinding.inflate(layoutInflater);
        return binding.root;
    }

}