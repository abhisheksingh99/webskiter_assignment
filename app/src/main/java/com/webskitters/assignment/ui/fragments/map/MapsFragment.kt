package com.webskitters.assignment.ui.fragments.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.webskitters.assignment.R
import com.webskitters.assignment.utils.LocationTracker
import com.webskitters.assignment.utils.ProviderError

class MapsFragment : Fragment() {
    private var marker: Marker? = null
    private lateinit var markerOptions: MarkerOptions
    private lateinit var mMap: GoogleMap
    private var latitude=0.0
    private var longitude=0.0

    private val callback = OnMapReadyCallback { googleMap ->

        mMap=googleMap
        val sydney = LatLng(latitude, longitude)
        markerOptions = MarkerOptions().position(sydney)
        marker = mMap.addMarker(markerOptions)
        marker?.setPosition(sydney)

         }

    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun OnTrack() {
        tracker.startListening(context = requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }

    var tracker: LocationTracker = LocationTracker(
        minTimeBetweenUpdates = 1000L, // one second
        minDistanceBetweenUpdates = 1F, // one meter
        shouldUseGPS = true,
        shouldUseNetwork = true,
        shouldUsePassive = true
    ).also {
        it.addListener(object : LocationTracker.Listener {
            override fun onLocationFound(location: Location) {

                latitude=location.latitude
                longitude=location.longitude
                val sydney = LatLng(latitude, longitude)
                marker?.setPosition(sydney);
                val location = CameraUpdateFactory.newLatLngZoom(
                    sydney, 15f
                )
                mMap!!.animateCamera(location)


            }

            override fun onProviderError(providerError: ProviderError) {
                TODO("Not yet implemented")
            }

        })
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        initTracker()
    }

    private fun initTracker() {
        val hasFineLocation = ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        val hasCoarseLocation = ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        if (!hasFineLocation || !hasCoarseLocation) {
            return ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1337);
        }
        else{
            OnTrack()
        }
    }

    override fun onResume() {
        super.onResume()
        initTracker()
    }

    override fun onDestroy() {
        super.onDestroy()
        tracker.stopListening(clearListeners = true)
    }


}