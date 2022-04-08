package com.example.pintaresfacil.views

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.pintaresfacil.Constants
import com.example.pintaresfacil.R
import com.example.pintaresfacil.database.AppDatabase
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class LocationClient : AppCompatActivity(),OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private lateinit var map: GoogleMap

    private var roomDatabase: AppDatabase? = null

companion object {
    const val REQUEST_CODE_LOCATION = 0
}

override fun onMapReady(googleMap: GoogleMap) {
    map = googleMap
    //createMarker()
    map.setOnMyLocationButtonClickListener(this)
    map.setOnMyLocationClickListener(this)
    enableLocation()
}


//Creamos el mapa
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_location_client)
    createMapFragment()
    initDatabase()


}

    private fun initDatabase() {
        roomDatabase =
            Room.databaseBuilder(this, AppDatabase::class.java, Constants.DATABASE_NAME).build()
    }

    //Desde el fragment cargaremos el mapa
private fun createMapFragment() {
    val mapFragment = supportFragmentManager
        .findFragmentById(R.id.fragmentMap) as SupportMapFragment
    mapFragment.getMapAsync(this)
}

private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
    this,
    Manifest.permission.ACCESS_FINE_LOCATION
) == PackageManager.PERMISSION_GRANTED


private fun enableLocation() {
    if (!::map.isInitialized) return
    if (isLocationPermissionGranted()) {
        map.isMyLocationEnabled = true
    } else {
        requestLocationPermission()
    }
}


private fun requestLocationPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    ) {
        Toast.makeText(this, "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
    } else {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_CODE_LOCATION
        )
    }
}

override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    when (requestCode) {
        REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
        } else {
            Toast.makeText(
                this,
                "Para activar la localización ve a ajustes y acepta los permisos",
                Toast.LENGTH_SHORT
            ).show()
        }
        else -> {}
    }
}

//COnprobamos los permisos cuando volvamos del background

override fun onResumeFragments() {
    super.onResumeFragments()
    if (!::map.isInitialized) return
    if (!isLocationPermissionGranted()) {
        map.isMyLocationEnabled = false
        Toast.makeText(
            this,
            "Para activar la localización ve a ajustes y acepta los permisos",
            Toast.LENGTH_SHORT
        ).show()
    }
}

override fun onMyLocationButtonClick(): Boolean {
    Toast.makeText(this, "Buscando Ubicación", Toast.LENGTH_SHORT).show()
    return false
}

    //Al puslar el icono de posicion marcara la latitud y la altitud
override fun onMyLocationClick(Ubication: Location) {

    val intent = Intent(this, ShoppingCartActivity::class.java)
    intent.putExtra("Ubication",Ubication)
    //intent.putExtra("Longitude",p0.longitude)
    startActivity(intent)
    Toast.makeText(this, "Ubicación mandada", Toast.LENGTH_SHORT).show()
      //  Toast.makeText(this, "Estás en ${p0.latitude}, ${p0.longitude}", Toast.LENGTH_SHORT).show()

  }
}








