package com.example.cursokotlinintermedio.ui.luck

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.example.cursokotlinintermedio.R
import com.example.cursokotlinintermedio.databinding.FragmentHoroscopeBinding
import com.example.cursokotlinintermedio.databinding.FragmentLuckBinding
import com.example.cursokotlinintermedio.ui.prodivers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint //esta clase sera inyectada
class LuckFragment : Fragment() {
    private var _binding: FragmentLuckBinding? = null;
    private val binding get() = _binding!!;

    @Inject
    lateinit var randomCardProvider: RandomCardProvider; //de esta manera se inyectan clases con @AndroidEntryPoint
    //creo por el ciclo de vida del componente
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLuckBinding.inflate(layoutInflater);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    private fun initUI() {
        preparePrediction();
        initListeners();
    }

    private fun preparePrediction() {
        val luck = randomCardProvider.getLucky();
        luck?.let { //para ejecutar en caso de no ser nulo
            val currentPrediction = getString(luck.text);
            binding.tvLucky.text = currentPrediction;
            binding.ivLuckyCard.setImageResource(luck.img);
            binding.tvShare.setOnClickListener { shareResult(currentPrediction) }
        }
    }

    private fun shareResult(currentPrediction: String) {
        val sendIntent = Intent().apply{//creamos el objeto a compartir
            action = Intent.ACTION_SEND;
            putExtra(Intent.EXTRA_TEXT, currentPrediction);
            type = "text/plain";
        }
        val shareIntent = Intent.createChooser(sendIntent, null); //este es el que comparte
        startActivity(shareIntent); //iniciamos a compartir
    }

    private fun initListeners() {
        binding.ivRutele.setOnClickListener{
            spinRutele();
        }
    }

    private fun spinRutele() {
        //funcion que llama a una animacion

        val random = Random();
        val dagress:Int = random.nextInt(1440) + 360; //escojemos un nun ramdom de 0 a 1440 grados y le sumamos 360

        //Creamos un objeto Animator, le asignamos la view, tipo de animacion y los grados de inicio y fin
        val animator = ObjectAnimator.ofFloat(binding.ivRutele, View.ROTATION, 0f, dagress.toFloat());
        animator.duration = 2000; //milisegunos
        animator.interpolator = DecelerateInterpolator();
        animator.doOnEnd {
            //En cuanto termine
            slideCard();
        }
        animator.start();//comenzamos con la animacion
    }

    private fun slideCard(){
        //esta es una animacion desde xml, por eso creamos una en xml dentro de res
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up);
        //En este tipo de animaiones ya no tenemos el doOnEnd o star, tenemos listeners
        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                binding.reverse.isVisible = true; //hacemos visible la carta
            }
            override fun onAnimationEnd(animation: Animation?) {
                growCard();
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        });
        binding.reverse.startAnimation(slideUpAnimation); //indicamos que el elemento inicie la animacion
    }

    private fun growCard() {
        //Este es otro metodo que utiliza un animation utils con xml
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow);
        growAnimation.setAnimationListener(object :AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                binding.reverse.isVisible = false;
                showPremonitionView();
            }
        });
        binding.reverse.startAnimation(growAnimation);
    }

    private fun showPremonitionView() {
        //este es otro tipo de animacion
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f);
        disappearAnimation.duration = 200;

        val appearAnimation = AlphaAnimation(0.0f, 1.0f);
        appearAnimation.duration = 1000;

        disappearAnimation.setAnimationListener(object : AnimationListener{
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                //cuando termine la animacion desaparecemos una frame y mostramos otro
                binding.preview.isVisible = false;
                binding.prediction.isVisible = true;
            }
        });
        binding.preview.startAnimation(disappearAnimation);
        binding.prediction.startAnimation(appearAnimation);
    }
}