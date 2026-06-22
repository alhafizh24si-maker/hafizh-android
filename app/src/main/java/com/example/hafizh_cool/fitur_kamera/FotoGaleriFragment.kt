package com.example.binadesa.Home.fitur_kamera

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.hafizh_cool.databinding.FragmentFotoGaleriBinding

class FotoGaleriFragment : Fragment() {
    private var _binding: FragmentFotoGaleriBinding? = null
    private val binding get() = _binding!!
    private var currentPhotoUri: Uri? = null

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            currentPhotoUri?.let { uri ->
                binding.ivCapturedImage.setImageURI(uri)
                context?.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
            }
        }
    }

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) openCamera() else Toast.makeText(context, "Izin kamera diperlukan", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFotoGaleriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCapture.setOnClickListener {
            if (hasCameraPermission()) openCamera() else permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun hasCameraPermission() = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        currentPhotoUri = createGalleryPhotoUri()
        intent.putExtra(MediaStore.EXTRA_OUTPUT, currentPhotoUri)
        cameraLauncher.launch(intent)
    }

    private fun createGalleryPhotoUri(): Uri {
        val folderName = "BinadesaCaptures"
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "BINA_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/$folderName")
        }
        return requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            ?: throw RuntimeException("Gagal membuat URI")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}