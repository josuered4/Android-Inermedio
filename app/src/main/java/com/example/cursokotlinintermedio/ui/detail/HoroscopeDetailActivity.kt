package com.example.cursokotlinintermedio.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.cursokotlinintermedio.R
import com.example.cursokotlinintermedio.databinding.ActivityHoroscopeDetailBinding
import com.example.cursokotlinintermedio.databinding.ActivityMainBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        initUI();
        horoscopeDetailViewModel.getHoroscope(args.type.name);
    }

    private fun initUI() {
        initListeners();
        initUIState();
    }

    private fun initListeners() {
        //la forma mas sencilla para regresar con onBackPressed
        binding.ivBack.setOnClickListener{onBackPressed()}
    }

    private fun initUIState() {
        //creamos una corrutina que se enganche al ciclo de vida del activiy en este caso
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){ //Ejecutate cuando el ciclo de vida del componente este en el estodo "STARTED"
                horoscopeDetailViewModel.state.collect{ //nos conectamos al viewmodel
                    when(it){
                        HoroscopeDetailState.Loading -> loadingState(); //si es el estado es loading mantenemos el progres var;
                        is HoroscopeDetailState.Error -> errorState();
                        is HoroscopeDetailState.Success -> succesState(it);
                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.progressBar.isVisible = true;
    }

    private fun errorState(){
        binding.progressBar.isVisible = false;
    }

    private fun succesState(state: HoroscopeDetailState.Success){
        binding.progressBar.isVisible = false;
        binding.tvTitle.text = state.prediction.sign;
        binding.tvBody.text = state.prediction.horoscope;
    }
}