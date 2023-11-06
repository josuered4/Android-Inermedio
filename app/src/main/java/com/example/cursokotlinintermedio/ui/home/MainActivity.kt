package com.example.cursokotlinintermedio.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.cursokotlinintermedio.R
import com.example.cursokotlinintermedio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding;

    private lateinit var navController:NavController;
    //NavController no ayuda con la gestion de navegacion con la libreria de Nav Component

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);
        initUI();
    }

    private fun initUI() {
        initNavigation();
    }

    private fun initNavigation() {
        //Buacamos el fragmentContainerView por el id para pasarlo al contorlador
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment;
        navController = navHost.navController; //inicializamos el controlador
        binding.bottonNavView.setupWithNavController(navController); //le pasamos el controlador a la barra de nav

    }
}