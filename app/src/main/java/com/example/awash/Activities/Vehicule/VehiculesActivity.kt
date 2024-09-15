package com.example.awash.Activities.Vehicule

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.a5equiz.bases.BaseActivity
import com.example.awash.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class VehiculesActivity : BaseActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vehicules)

        val iconMenu = findViewById<ImageView>(R.id.iconMenu)
        val addNewVehiculeBtn = findViewById<FloatingActionButton>(R.id.addNewVehiculeBtn)
        val btnEdit = findViewById<Button>(R.id.btnEdit)

        iconMenu.setOnClickListener {
            showDrawerBottomFragment()
        }

        addNewVehiculeBtn.setOnClickListener {
            val intent = Intent(this, AddVehiculeActivity::class.java)
            startActivity(intent)
        }

        btnEdit.setOnClickListener {
            startActivity(Intent(this, EditVehiculeActivity::class.java))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun expand(view: View) {

        val layoutBox = findViewById<LinearLayout>(R.id.layoutBox)
        val btnLayout = findViewById<LinearLayout>(R.id.btnLayout)
        val v = if (btnLayout.visibility == View.GONE) View.VISIBLE else View.GONE

        TransitionManager.beginDelayedTransition(layoutBox, AutoTransition())
        btnLayout.visibility = v
    }
}
