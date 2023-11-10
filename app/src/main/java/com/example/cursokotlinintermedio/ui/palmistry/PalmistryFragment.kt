package com.example.cursokotlinintermedio.ui.palmistry

import android.app.Instrumentation.ActivityResult
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker
import com.example.cursokotlinintermedio.Manifest
import com.example.cursokotlinintermedio.R
import com.example.cursokotlinintermedio.databinding.FragmentPalmistryBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import java.security.Permission

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

        }else{
            requestPermissionLauncher.launch(CAMERA_PERMISSION);
        }
    }

    fun checkCameraPermission(): Boolean {
        return PermissionChecker.checkSelfPermission(requireContext(), CAMERA_PERMISSION) == PermissionChecker.PERMISSION_GRANTED;
    }
}