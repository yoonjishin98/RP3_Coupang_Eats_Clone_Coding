package com.yoonji.coupangeatsproject.src

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.yoonji.coupangeatsproject.BaseActivity
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.ActivityAddressByMapBinding
import java.security.Permission
import java.util.*
import java.util.jar.Manifest

class AddressByMapActivity : BaseActivity<ActivityAddressByMapBinding>(ActivityAddressByMapBinding::inflate),
    OnMapReadyCallback {

    companion object{
        lateinit var mNaverMap:NaverMap
        lateinit var locationSourece :FusedLocationSource

        var PERMISSIONS = arrayOf<String>(
            ACCESS_FINE_LOCATION,
            ACCESS_COARSE_LOCATION
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //지도 객체 생성
        var mapFragment = supportFragmentManager.findFragmentById(R.id.fragment_map)
        if(mapFragment == null){
            Log.d("TAG", "null값")
            mapFragment = MapFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragment_map, mapFragment).commit()
        }

        //getMapAsync 호출해 비동기로 onMapReady 콜백 메서드 호출. onMapReady에서 NaverMap 객체 받음
        MapFragment.newInstance().getMapAsync(this)

        locationSourece = FusedLocationSource(this,100)

    }

    override fun onResume() {
        super.onResume()

        binding.imgvMapBack.setOnClickListener{
            finish()
        }
    }

    override fun onMapReady(p0: NaverMap){
        Log.d( "TAG", "onMapReady");

        val addressMarker = Marker()
        addressMarker.position = LatLng(37.5670135,37.5670135)
        addressMarker.map = p0

        mNaverMap = p0
        mNaverMap.locationSource = locationSourece

        ActivityCompat.requestPermissions(this,PERMISSIONS,100)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // request code와 권한획득 여부 확인
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mNaverMap.locationTrackingMode = LocationTrackingMode.Follow;
            }
        }
    }
}