package com.example.awash.Lavage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.awash.HomeActivity
import com.example.awash.R
import androidx.constraintlayout.widget.R.layout.*

class AddLavageTypeActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_lavage_type)

        val iconBack = findViewById<ImageButton>(R.id.iconBack)
        val textView = findViewById<TextView>(R.id.price)
        val prixLayout = findViewById<LinearLayout>(R.id.prixLayout)
        val saveTypeButton = findViewById<Button>(R.id.saveTypeButton)
        val okayBtn = findViewById<Button>(R.id.okayBtn)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        saveTypeButton.setOnClickListener {
            val view = View.inflate(this, R.layout.dialog_view_success, null)

            val builder = AlertDialog.Builder(this)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        }

        iconBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            prixLayout.visibility = View.VISIBLE
            when (checkedId) {
                R.id.type1 -> textView.text = "2000".plus(" XOF")
                R.id.type2 -> textView.text = "5000".plus(" XOF")
                R.id.type3 -> textView.text = "15000".plus(" XOF")
                R.id.type4 -> textView.text = "20000".plus(" XOF")
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}