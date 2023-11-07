package com.example.cursokotlinintermedio.ui.horoscope

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cursokotlinintermedio.R
import com.example.cursokotlinintermedio.databinding.FragmentHoroscopeBinding
import com.example.cursokotlinintermedio.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint // habilitar la inyección de dependencias en Android
class HoroscopeFragment : Fragment() {

    private val horoscopeViewModel by viewModels<HoroscopeViewModel>();
    //inyeccion especial de los viewModel, dado a que
    // no solo se usa la inyeccion de dependencias
    //sino la creacion de viewModels del tipo "HoroscopeViewModel"

    private lateinit var adapter:HoroscopeAdapter; //definimos el adaptador del adapter del recycle view

    private var _binding:FragmentHoroscopeBinding? = null;
    private val binding get() = _binding!!;

    //A diferencia de los activities, esta funcion crea el Fragment
    override fun onCreateView(
        //funcion para crear la vista, pero para metodos de conf no se recomienda usarlo
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater,container,false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //funcion que se ejecuta cuando la vista ya fue creada, como el DOMContentLoaded en js
        //en este caso la usaremos para inicializar la unit
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    private fun initUI() {
        //funcion para inicializar la ui
        initUIState();
        initRecycleView();
    }
    private fun initRecycleView() {
        adapter = HoroscopeAdapter(); //inicializamos la lista
        /*binding.rvHoroscope.apply{
            layoutManager = LinearLayoutManager(context);//organiza los item del recycleview, en este caso esta en vertical
            adapter = this.adapter; //agragmos el adaptador a la vista
        }*/
        //binding.rvHoroscope.layoutManager = LinearLayoutManager(context)
        binding.rvHoroscope.layoutManager = GridLayoutManager(context, 2); //cambiamos el linear por un Grid con 2 column
        binding.rvHoroscope.adapter = adapter
    }

    //funcion para conectar el viewModel al view
    private fun initUIState() {

        //lifecycleScope es un tipo de corrutina, en este tipo de corrutina su
        //ciclo de vida engancha al ciclo de vida del elemento en el que se encuentra
        //en este caso morira con el fragment
        lifecycleScope.launch{ //con launch se crea una corrutina
            repeatOnLifecycle(Lifecycle.State.STARTED){
                //Ejecutate cuando el ciclo de vida del componente este en el estodo "STARTED"
                horoscopeViewModel.horoscopos.collect{
                    //collect, comenzamos la comuniconacion view-ViewModel
                    data -> adapter.updateList(data); //it es el listado que manda el viewModel
                }
            }
        }
    }
}