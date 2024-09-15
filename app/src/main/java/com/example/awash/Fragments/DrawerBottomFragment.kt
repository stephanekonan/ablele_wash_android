package com.example.awash.Fragments

import android.app.ActivityOptions
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.example.awash.Activities.Auth.LoginActivity
import com.example.awash.Activities.Auth.ProfileUserActivity
import com.example.awash.Activities.HistoryActivity
import com.example.awash.Activities.HomeActivity
import com.example.awash.Activities.Lavage.LavagesDoneActivity
import com.example.awash.R
import com.example.awash.Activities.Vehicule.VehiculesActivity
import com.example.awash.databinding.FragmentDrawerBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DrawerBottomFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDrawerBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDrawerBottomBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }
    private fun setClickListeners() {

        binding.closeButtomLayout.setOnClickListener {
            dismiss()
        }

        binding.profile.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            startActivity(Intent(activity, ProfileUserActivity::class.java), bundle)
            dismiss()
        }
        binding.home.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            startActivity(Intent(activity, HomeActivity::class.java), bundle)
            dismiss()
        }
        binding.hostory.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            startActivity(Intent(activity, HistoryActivity::class.java), bundle)
            dismiss()
        }
        binding.lavagesDone.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            startActivity(Intent(activity, LavagesDoneActivity::class.java), bundle)
            dismiss()
        }
        binding.vehicules.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            startActivity(Intent(activity, VehiculesActivity::class.java), bundle)
            dismiss()
        }
        binding.btnLogout.setOnClickListener {
            activity?.let { it1 ->
            }
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            startActivity(Intent(activity, LoginActivity::class.java), bundle)
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

}