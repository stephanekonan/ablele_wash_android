package com.example.awash.Activities.Vehicule

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a5equiz.bases.BaseActivity
import com.example.awash.R
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.util.FileUtil
import java.io.File

class AddVehiculeActivity : BaseActivity() {

    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private lateinit var sharedPreferences: android.content.SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_vehicule)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val addImageLayoutBtn = findViewById<LinearLayout>(R.id.addImageLayoutBtn)
        val iconMenu = findViewById<ImageView>(R.id.iconMenu)

        iconMenu.setOnClickListener {
            showDrawerBottomFragment()
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val vehicleImage = findViewById<ImageView>(R.id.vehicleImage)

        saveButton.setOnClickListener {
            val intent = Intent(this, VehiculesActivity::class.java)
            startActivity(intent)
        }

        val saveImageUri = sharedPreferences.getString("imageUrl", null)
        if (saveImageUri != null) {
            vehicleImage.setImageURI(Uri.parse(saveImageUri))
        }

        imagePickerLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val imageUri = result.data?.data
                if (imageUri != null) {
                    val file = FileUtil.getTempFile(this, imageUri)
                    vehicleImage.setImageURI(imageUri)
                    val editor = sharedPreferences.edit()
                    editor.putString("imageUrl", imageUri.toString())
                    editor.apply()
                }
            }
        }

        addImageLayoutBtn.setOnClickListener {
            ImagePicker.with(this)
                .galleryMimeTypes(
                    mimeTypes = arrayOf(
                        "image/png",
                        "image/jpg",
                        "image/jpeg"
                    )
                )
                .crop()
                .compress(1024)
                .maxResultSize(1920, 1080)
                .createIntent { intent ->
                    imagePickerLauncher.launch(intent)
                }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun isValidImageType(file: File): Boolean {
        val mimeType = contentResolver.getType(Uri.fromFile(file))
        val validMimeTypes = listOf("image/png","image/jpg","image/jpeg")
        return validMimeTypes.contains(mimeType)
    }

    private fun isValidImageSize(file: File): Boolean {
        val maxSizeInBytes = 1920 * 1080
        return file.length() <= maxSizeInBytes
    }

}
