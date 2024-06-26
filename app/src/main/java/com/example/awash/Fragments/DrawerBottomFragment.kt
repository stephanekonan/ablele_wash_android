package com.example.awash.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.awash.Config.HistoryActivity
import com.example.awash.HomeActivity
import com.example.awash.Vehicule.VehiculesActivity
import com.example.awash.databinding.FragmentDrawerBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DrawerBottomFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDrawerBottomBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDrawerBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.home.setOnClickListener {
            startActivity(Intent(context, HomeActivity::class.java))
        }

        binding.hostory.setOnClickListener {
            startActivity(Intent(context, HistoryActivity::class.java))
        }

        binding.Vehicules.setOnClickListener {
            startActivity(Intent(context, VehiculesActivity::class.java))
        }
    }

}