package com.yoonji.coupangeatsproject.src.address_detail

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityAddressDetailBinding
import com.yoonji.coupangeatsproject.src.address_detail.models.AddressDetailRequest
import com.yoonji.coupangeatsproject.src.address_detail.models.AddressDetailResponse
import com.yoonji.coupangeatsproject.src.main.MainActivity
import com.yoonji.coupangeatsproject.src.sign_up.SignUpService
import com.yoonji.coupangeatsproject.src.sign_up.models.PostSignUpRequest
import kotlin.concurrent.thread

class AddressDetailActivity : BaseActivity<ActivityAddressDetailBinding>(ActivityAddressDetailBinding::inflate)
    ,AddressDetailActivityView{

    private var TAG = "***AddressDetailActivity----->"
    var latitude = 0.0
    var longitude = 0.0
    var iRoadAddress = ""
    var iAddress = ""
    val userIdxFromSharedPreferences = ApplicationClass.sSharedPreferences.getInt("userIdx", -1)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        iAddress = intent.getStringExtra("address").toString()
        iRoadAddress = intent.getStringExtra("roadAddress").toString()

        binding.tvAddressDetailAddress.text = iAddress
        binding.tvAddressDetailRoad.text = iRoadAddress

        //주소 -> 좌표 by Geocoder
        thread(start = true){
            if (iAddress != null) {
                AddressToLatLng(iAddress)
            }
        }

    }

    fun AddressToLatLng(address:String){
        //var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if(ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            showCustomToast("위치 권한을 설정해주세요")
        }else{
            val geocoder = Geocoder(this)
            val latlng = geocoder.getFromLocationName(address,1)
            latitude = latlng[0].latitude
            longitude = latlng[0].longitude
            //Log.d("TAG ", "AddressToLatLng 결과: 위도) " + latlng[0].latitude + ", 경도) " + latlng[0].longitude)

            // 상세 주소 api
            val addressRequest = AddressDetailRequest(userIdx = userIdxFromSharedPreferences,
                address = iRoadAddress,
                latitude = latitude,
                longtitude = longitude)
            AddressDetailService(this).postAddressDetail(addressRequest)

        }
    }

    override fun onResume() {
        super.onResume()

        binding.imgvAddressDetailBack.setOnClickListener { finish() }
    }

    override fun onPostAddressDetailSuccess(response: AddressDetailResponse) {
        Log.d(TAG, "onPostAddressDetailSuccess: $response")

        if(response.isSuccess){
            binding.btnAddressDetailFinish.setOnClickListener{
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("changeAddress",111)
                intent.putExtra("newAddress",iAddress)
                Log.d(TAG, "iAddress 값: $iAddress")
                startActivity(intent)
            }
        }else{
            Log.d(TAG, "onPostAddressDetailSuccess 실패 메시지: " + response.message)
        }

    }

    override fun onPostAddressDetailFailure(message: String) {
        Log.d(TAG, "오류 : $message")
    }

}