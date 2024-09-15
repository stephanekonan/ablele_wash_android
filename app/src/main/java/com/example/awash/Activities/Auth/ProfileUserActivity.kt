package com.example.awash.Activities.Auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a5equiz.bases.BaseActivity
import com.example.awash.Config.ConstToast
import com.example.awash.R

class ProfileUserActivity : BaseActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile_user)
 
        val iconBack = findViewById<ImageButton>(R.id.iconBack)
        val editUserBtn = findViewById<ImageButton>(R.id.editUserBtn)
        val editDataLayout = findViewById<LinearLayout>(R.id.editDataLayout)
        val userDataLayout = findViewById<LinearLayout>(R.id.userDataLayout)
        val updateBtn = findViewById<Button>(R.id.updateBtn)

        iconBack.setOnClickListener {
            finish()
        }
        editUserBtn.setOnClickListener {
            editDataLayout.visibility = View.VISIBLE
            userDataLayout.visibility = View.GONE
        }
        updateBtn.setOnClickListener {
            editDataLayout.visibility = View.GONE
            userDataLayout.visibility = View.VISIBLE
            showToast(ConstToast.TOAST_TYPE_SUCCESS, "Modification effectuée avec succès")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}