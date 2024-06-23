package com.example.awash

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.awash.Lavage.AddLavageTypeActivity
import com.example.awash.databinding.ActivityMainBinding
import com.example.awash.service.Lavage
import com.example.awash.service.LavageService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import www.sanju.motiontoast.MotionToast
import java.io.IOException

class HomeActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mGoogleMap: GoogleMap
    private lateinit var location: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var service: LavageService

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        retrofit = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8000/api/client/lavages/aproximity")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(LavageService::class.java)

        val sheetBottom: FrameLayout = findViewById(R.id.sheetBottom)
        BottomSheetBehavior.from(sheetBottom).apply {
            peekHeight = 500
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        val iconMenu = findViewById<ImageView>(R.id.iconMenu)

        iconMenu.setOnClickListener {
            val view: View = layoutInflater.inflate(R.layout.fragment_drawer_bottom, null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(view)
            dialog.show()
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val iconSearch = findViewById<ImageButton>(R.id.iconSearch)
        iconSearch.setOnClickListener {
            showSearchDialog()
        }

        val gridLayout = findViewById<GridLayout>(R.id.grid)
        gridLayout.setOnClickListener {
            val intent = Intent(this, AddLavageTypeActivity::class.java)
            startActivity(intent);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
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
            if (it != null) {
                val currentLatLng = LatLng(it.latitude, it.longitude)
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))

                fetchNearbyLavages(it.latitude, it.longitude)
            }
        }
    }

    private fun fetchNearbyLavages(latitude: Double, longitude: Double) {
        val call = service.getNearbyLavages(latitude, longitude, 10)
        call.enqueue(object : Callback<List<Lavage>> {
            override fun onResponse(call: Call<List<Lavage>>, response: Response<List<Lavage>>) {
                if (response.isSuccessful) {
                    val lavages = response.body()
                    lavages?.forEach { lavage ->
                        val position = LatLng(lavage.latitude, lavage.longitude)
                        placeMarkerOnMap(position, lavage.lavageName)
                    }
                }
            }

            override fun onFailure(call: Call<List<Lavage>>, t: Throwable) {
                MotionToast.createColorToast(this@HomeActivity,"Erreur lors de la récupération",
                    MotionToast.TOAST_ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    1000,
                    ResourcesCompat.getFont(this@HomeActivity, R.font.poppinsregular))
            }
        })
    }


    private fun placeMarkerOnMap(location: LatLng, title: String) {
        val markerOptions = MarkerOptions().position(location).title(title)
        mGoogleMap.addMarker(markerOptions)
    }

    private fun showSearchDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Rechercher une ville ou commune")
        val input = EditText(this)
        builder.setView(input)
        builder.setPositiveButton("Rechercher") { dialog, _ ->
            val location = input.text.toString()
            searchLocation(location)
            dialog.dismiss()
        }
        builder.setNegativeButton("Annuler") { dialog, _ ->
            dialog.cancel()
        }
        builder.show()
    }

    private fun searchLocation(location: String) {
        val geocoder = Geocoder(this)
        try {
            val addresses = geocoder.getFromLocationName(location, 1)
            if (addresses != null) {
                if (addresses.isNotEmpty()) {
                    val address = addresses.get(0)
                    val latLng = LatLng(address.latitude, address.longitude)
                    mGoogleMap.addMarker(MarkerOptions().position(latLng).title(location))
                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                } else {
                    Snackbar.make(findViewById(R.id.main), "Location not found", Snackbar.LENGTH_LONG).show()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Snackbar.make(findViewById(R.id.main), "Error finding location", Snackbar.LENGTH_LONG).show()
        }
    }
}