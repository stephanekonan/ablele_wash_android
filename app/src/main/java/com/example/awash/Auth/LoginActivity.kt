package com.example.awash.Auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.awash.HomeActivity
import com.example.awash.R
import com.example.awash.Vehicule.AddVehiculeActivity
import www.sanju.motiontoast.MotionToast

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val loginButton = findViewById<TextView>(R.id.loginButton)
        val toCreateNewAccount = findViewById<TextView>(R.id.createNewAccountText)

        loginButton.setOnClickListener {
            MotionToast.createColorToast(this,"Vous êtes bien connectés",
                MotionToast.TOAST_SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                1000,
                ResourcesCompat.getFont(this, R.font.poppinsregular))
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        toCreateNewAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}