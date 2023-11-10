package com.example.cursokotlinintermedio.ui.palmistry

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.core.impl.UseCaseConfig
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.example.cursokotlinintermedio.databinding.FragmentPalmistryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PalmistryFragment : Fragment() {

    private var _binding:FragmentPalmistryBinding? = null;
    private val binding get() = _binding!!;

    companion object {
        private const val CAMERA_PERMISSION = android.Manifest.permission.CAMERA;
    }

    //con esto hacemos una peticion de permisos
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ isGranted ->
        if(isGranted){
            startCamera();
        }else{
            Toast.makeText(requireContext(), "Se requiere de la camara", Toast.LENGTH_LONG).show();
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPalmistryBinding.inflate(layoutInflater);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(checkCameraPermission()){
            startCamera();
        }else{
            requestPermissionLauncher.launch(CAMERA_PERMISSION);
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext());
        //creamos un procesador o gestor para la pantalla

        //agregamos un listener de eventos
        cameraProviderFuture.addListener({
            //obtenemos un el future provider para obtener su ciclo de vida
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get();
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.viewFinder.surfaceProvider);//cargamos la preview a la vista
            }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA; //abrimos la camara de atras
            try{
                cameraProvider.unbindAll(); //desbindea todo
                cameraProvider.bindToLifecycle(this, cameraSelector, preview);
            }catch (e:Exception){
                Log.i("EROOR CAMERA", "startCamera: ERROR");
            }
        }, ContextCompat.getMainExecutor(requireContext()));
    }

    fun checkCameraPermission(): Boolean {
        return PermissionChecker.checkSelfPermission(requireContext(), CAMERA_PERMISSION) == PermissionChecker.PERMISSION_GRANTED;
    }
}