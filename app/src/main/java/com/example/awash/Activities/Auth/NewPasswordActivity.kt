package com.example.awash.Activities.Auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a5equiz.bases.BaseActivity
import com.example.awash.Config.ConstToast
import com.example.awash.R

class NewPasswordActivity : BaseActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_password)

        val iconBack = findViewById<ImageButton>(R.id.iconBack)
        val saveNewPswButton = findViewById<Button>(R.id.saveNewPswButton)

        iconBack.setOnClickListener {
            val intent = Intent(this, SendForgetPasswordActivity::class.java)
            startActivity(intent)
        }
        saveNewPswButton.setOnClickListener {
            showToast(ConstToast.TOAST_TYPE_SUCCESS,"Mot de passe modifié avec succès")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}