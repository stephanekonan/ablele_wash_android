package com.example.awash.Activities.Auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a5equiz.bases.BaseActivity
import com.example.awash.Config.ConstToast
import com.example.awash.R

class SendForgetPasswordActivity : BaseActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_send_forget_password)

        val iconBack = findViewById<ImageButton>(R.id.iconBack)
        val sendCodeButton = findViewById<Button>(R.id.sendCodeButton)
        val toLoginActivity = findViewById<TextView>(R.id.toLoginActivity)

        iconBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        sendCodeButton.setOnClickListener {
            showToast(ConstToast.TOAST_TYPE_SUCCESS, "Code envoyé avec succès")
            val intent = Intent(this, CodeForgetPasswordActivity::class.java)
            startActivity(intent)
        }
        toLoginActivity.setOnClickListener {
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