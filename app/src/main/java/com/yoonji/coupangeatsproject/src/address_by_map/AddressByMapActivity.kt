package com.yoonji.coupangeatsproject.src.address_by_map

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityAddressByMapBinding
import java.io.IOException
import java.util.*


class AddressByMapActivity : BaseActivity<ActivityAddressByMapBinding>(ActivityAddressByMapBinding::inflate),
    OnMapReadyCallback {

    companion object{
        lateinit var locationSource :FusedLocationSource
        val LOCATION_PERMISSION_REQUEST_CODE = 1000

        var PERMISSIONS = arrayOf<String>(
            ACCESS_FINE_LOCATION,
            ACCESS_COARSE_LOCATION
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationSource = FusedLocationSource(this,LOCATION_PERMISSION_REQUEST_CODE)
        var option:NaverMapOptions = NaverMapOptions().scaleBarEnabled(false)

        //지도 객체 생성
        var mapFragment:MapFragment = supportFragmentManager.findFragmentById(R.id.fragment_map) as MapFragment
        if(mapFragment == null){
            mapFragment = MapFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragment_map, mapFragment).commit()
        }
        mapFragment.getMapAsync(this)

    }

    override fun onResume() {
        super.onResume()

        binding.imgvMapBack.setOnClickListener{ finish() }

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onMapReady(naverMap: NaverMap){

        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        val uiSettings: UiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true

        val marker = Marker()
        marker.position = LatLng(36.763695,127.281796)
        marker.map = naverMap
        marker.icon = OverlayImage.fromResource(R.drawable.ic_marker);
        marker.width = 50
        marker.height = 50
    }



}