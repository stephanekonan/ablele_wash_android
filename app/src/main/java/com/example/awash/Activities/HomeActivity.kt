package com.example.awash.Activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a5equiz.bases.BaseActivity
import com.example.awash.Activities.Lavage.ProfileLavageActivity
import com.example.awash.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : BaseActivity(), OnMapReadyCallback {

    private lateinit var mGoogleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingInflatedId", "WrongViewCast", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(R.layout.activity_home)
        window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)

        val sheetBottom = findViewById<FrameLayout>(R.id.sheetBottom)
        val imageBtn = findViewById<ImageButton>(R.id.imageBtn)
        val iconMenu = findViewById<ImageView>(R.id.iconMenu)
        val gridLayout = findViewById<GridLayout>(R.id.grid)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        val sheetBottomSearch = findViewById<FrameLayout>(R.id.searchLavageBottom)
        val floatingactionbutton = findViewById<FloatingActionButton>(R.id.fab)
        val closeButtomLayout = findViewById<LinearLayout>(R.id.closeButtomLayout)

        BottomSheetBehavior.from(sheetBottom).apply {
            peekHeight = 800
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        BottomSheetBehavior.from(sheetBottomSearch).apply {
            setPeekHeight(0, true)
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        imageBtn.setOnClickListener {
            BottomSheetBehavior.from(sheetBottom).apply {
                peekHeight = 500
                this.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
        iconMenu.setOnClickListener {
            showDrawerBottomFragment()
        }
        gridLayout.setOnClickListener {
            val intent = Intent(this, ProfileLavageActivity::class.java)
            startActivity(intent)
        }
        floatingactionbutton.setOnClickListener {
            BottomSheetBehavior.from(sheetBottomSearch).apply {
                setPeekHeight(-1, true)
                this.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            sheetBottomSearch.visibility = View.VISIBLE
        }
        closeButtomLayout.setOnClickListener {
            BottomSheetBehavior.from(sheetBottomSearch).apply {
                setPeekHeight(0, true)
                this.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.uiSettings.isMyLocationButtonEnabled = false
        mGoogleMap = googleMap
        mGoogleMap.uiSettings.isZoomControlsEnabled = true
        setUpMap()
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }
        mGoogleMap.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this) {
            location -> location?.let {
                val currentLatLng = LatLng(it.latitude, it.longitude)
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                placeMarkerOnMap(currentLatLng, "google map")
            }
        }
    }

    private fun placeMarkerOnMap(location: LatLng, title: String) {
        val markerOptions = MarkerOptions().position(location).title(title)
        mGoogleMap.addMarker(markerOptions)
    }

}