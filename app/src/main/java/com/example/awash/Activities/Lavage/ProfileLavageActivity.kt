package com.example.awash.Activities.Lavage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a5equiz.bases.BaseActivity
import com.example.awash.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProfileLavageActivity : BaseActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(R.layout.activity_profile_lavage)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val iconMenu = findViewById<ImageView>(R.id.iconMenu)

        iconMenu.setOnClickListener {
            showDrawerBottomFragment()
        }

        val btnMakeLavage = findViewById<Button>(R.id.newLavageWatch)
        btnMakeLavage.setOnClickListener {
            val intent = Intent(this, AddLavageTypeActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}