package com.example.awash.Activities.Lavage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a5equiz.bases.BaseActivity
import com.example.awash.Activities.HomeActivity
import com.example.awash.R
import com.example.awash.Activities.HistoryActivity

class AddLavageTypeActivity : BaseActivity() {
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_lavage_type)

        val iconBack = findViewById<ImageButton>(R.id.iconBack)
        val textView = findViewById<TextView>(R.id.price)
        val prixLayout = findViewById<LinearLayout>(R.id.prixLayout)
        val saveTypeButton = findViewById<Button>(R.id.saveTypeButton)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        saveTypeButton.setOnClickListener {
            val view = View.inflate(this, R.layout.dialog_view_success, null)
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            android.os.Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, HistoryActivity::class.java)
                startActivity(intent)
            }, 3000)

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