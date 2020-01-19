package com.example.songily

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_map1.*
import java.util.*
import android.widget.Toast
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import android.provider.Settings
import com.google.android.material.snackbar.Snackbar


class ActivityMap1 : AppCompatActivity() {

    val PERMISSION_ID = 42
    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    val distance1 = FloatArray(2)
    val distance2 = FloatArray(2)
    val distance3 = FloatArray(2)
    val distance4 = FloatArray(2)
    val circle1 = CircleOptions()
    val circle2 = CircleOptions()
    val circle3 = CircleOptions()
    val circle4 = CircleOptions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map1)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {

            googleMap = it
            googleMap.isMyLocationEnabled = true
            googleMap.uiSettings.isZoomControlsEnabled = true

            val bayX = 51.618868
            val bayY = -3.880065

            val u = 51.618868 + 0.003
            val v = 51.618868 - 0.003

            val x = -3.880065 + 0.003
            val y = -3.880065 - 0.003

            val location = LatLng(bayX, bayY)
            val location1 =
                LatLng(v + (u - v) * Random().nextDouble(), y + (x - y) * Random().nextDouble())
            val location2 =
                LatLng(v + (u - v) * Random().nextDouble(), y + (x - y) * Random().nextDouble())
            val location3 =
                LatLng(v + (u - v) * Random().nextDouble(), y + (x - y) * Random().nextDouble())
            val location4 =
                LatLng(v + (u - v) * Random().nextDouble(), y + (x - y) * Random().nextDouble())

            //val circle1 = CircleOptions()
            //googleMap.addMarker(MarkerOptions().position(location).title("my location"))
            googleMap.addMarker(MarkerOptions().position(location1))
            googleMap.addMarker(MarkerOptions().position(location2))
            googleMap.addMarker(MarkerOptions().position(location3))
            googleMap.addMarker(MarkerOptions().position(location4))

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))

            googleMap.addCircle(
                circle1
                    .center(location1)
                    .radius(70.0)
                    .strokeWidth(2f)
                    .strokeColor(Color.rgb(73, 56, 41))
                    .fillColor(Color.argb(70, 133, 87, 35))
            )

            googleMap.addCircle(
                circle2
                    .center(location2)
                    .radius(70.0)
                    .strokeWidth(2f)
                    .strokeColor(Color.rgb(73, 56, 41))
                    .fillColor(Color.argb(70, 133, 87, 35))
            )

            googleMap.addCircle(
                circle3
                    .center(location3)
                    .radius(70.0)
                    .strokeWidth(2f)
                    .strokeColor(Color.rgb(73, 56, 41))
                    .fillColor(Color.argb(70, 133, 87, 35))
            )

            googleMap.addCircle(
                circle4
                    .center(location4)
                    .radius(70.0)
                    .strokeWidth(2f)
                    .strokeColor(Color.rgb(73, 56, 41))
                    .fillColor(Color.argb(70, 133, 87, 35))
            )


        })

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()
        requestNewLocationData()

        val fab2 = findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        val fab1 = findViewById<FloatingActionButton>(R.id.floatingActionButton1)

        fab2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val snackbar = Snackbar.make(v, "Enter a circle", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        })
        fab1.setOnClickListener {
            val intent = Intent(this, AR1::class.java)
            startActivity(intent)
        }

    }

    // Simple function to show/hide our AR1 FAB
    @SuppressLint("RestrictedApi")
    private fun showFab1(enabled: Boolean) {
        if (enabled) {
            floatingActionButton1.isEnabled = true
            floatingActionButton1.visibility = View.VISIBLE
            floatingActionButton2.isEnabled = false
            floatingActionButton2.visibility = View.GONE
        } else {
            floatingActionButton1.isEnabled = false
            floatingActionButton1.visibility = View.GONE
            floatingActionButton2.isEnabled = true
            floatingActionButton2.visibility = View.VISIBLE
        }
    }

    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        var lat = location.latitude
                        var long = location.longitude

                        var accuracy = location.accuracy

                        val lastLoc = LatLng(lat, long)

                        //googleMap.addMarker(MarkerOptions().position(lastLoc).title("Where you are"))
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    private fun requestNewLocationData() {
        Log.i("myLocation", "request")
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 2000
        mLocationRequest.fastestInterval = 1000

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            Log.i("myLocation", "Callback")
            var mLastLocation: Location = locationResult.lastLocation
            var lat = mLastLocation.latitude
            var long = mLastLocation.longitude

            //val lastLoc = LatLng(lat, long)

            if (distance1[0] > circle1.radius && distance2[0] > circle2.radius &&
                distance3[0] > circle3.radius && distance4[0] > circle4.radius
            ) {

                Location.distanceBetween(
                    lat, long,
                    circle1.center.latitude, circle1.center.longitude, distance1
                )
                Location.distanceBetween(
                    lat, long,
                    circle2.center.latitude, circle2.center.longitude, distance2
                )
                Location.distanceBetween(
                    lat, long,
                    circle3.center.latitude, circle3.center.longitude, distance3
                )
                Location.distanceBetween(
                    lat, long,
                    circle4.center.latitude, circle4.center.longitude, distance4
                )
                showFab1(false)
                Log.d("its out", "bad")
            } else {
                Location.distanceBetween(
                    lat, long,
                    circle1.center.latitude, circle1.center.longitude, distance1
                )
                Location.distanceBetween(
                    lat, long,
                    circle2.center.latitude, circle2.center.longitude, distance2
                )
                Location.distanceBetween(
                    lat, long,
                    circle3.center.latitude, circle3.center.longitude, distance3
                )
                Location.distanceBetween(
                    lat, long,
                    circle4.center.latitude, circle4.center.longitude, distance4
                )
                showFab1(true)
                Log.d("its in", "good")
            }
        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }

}