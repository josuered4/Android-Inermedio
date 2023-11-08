package com.example.cursokotlinintermedio.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.navArgs
import com.example.cursokotlinintermedio.R
import com.example.cursokotlinintermedio.databinding.ActivityHoroscopeDetailBinding
import com.example.cursokotlinintermedio.databinding.ActivityMainBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHoroscopeDetailBinding;
    private val horoscopeDetailViewModel:HoroscopeDetailViewModel by viewModels<HoroscopeDetailViewModel>();

    //Forma segura de recibir argumentos, con la librera de navigation.safeargs
    private val args:HoroscopeDetailActivityArgs by navArgs();
    //Los args son mandados con forma de un array, como en js al hacer un fect pero
    //fuertemente tipados.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater);
        setContentView(binding.root);
    }
}