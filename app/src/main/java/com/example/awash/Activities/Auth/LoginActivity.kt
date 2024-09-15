package com.example.awash.Activities.Auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import com.example.a5equiz.bases.BaseActivity
import com.example.awash.Activities.HomeActivity
import com.example.awash.Config.ConstToast
import com.example.awash.R

class LoginActivity : BaseActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val loginButton = findViewById<TextView>(R.id.loginButton)
        val toCreateNewAccount = findViewById<TextView>(R.id.createNewAccountText)
        val forgottenPasswordText = findViewById<TextView>(R.id.forgottenPasswordText)
        val loaderAwash = findViewById<LottieAnimationView>(R.id.loaderAwash)

        loginButton.setOnClickListener {
            loginButton.visibility = View.GONE
            loaderAwash.visibility = View.VISIBLE
            showToast(ConstToast.TOAST_TYPE_SUCCESS, "Vous êtes bien connecté(es)")
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        toCreateNewAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        forgottenPasswordText.setOnClickListener {
            val intent = Intent(this, SendForgetPasswordActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}