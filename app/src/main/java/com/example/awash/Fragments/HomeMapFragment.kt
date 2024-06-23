package com.example.awash.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.awash.databinding.FragmentHomeMapBinding

class HomeMapFragment : Fragment() {

    private lateinit var binding: FragmentHomeMapBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeMapBinding.inflate(inflater, container, false)
        return binding.root
    }

}