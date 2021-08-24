package com.yoonji.coupangeatsproject.src.address_by_search

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityAddressBySearchBinding
import com.yoonji.coupangeatsproject.src.address_by_map.KakaoApi
import com.yoonji.coupangeatsproject.src.address_by_map.KakaoApiRetrofitClient
import com.yoonji.coupangeatsproject.src.address_by_map.data.RoadAddress
import com.yoonji.coupangeatsproject.src.main.home.adapter.FoodTypeAdapter
import com.yoonji.coupangeatsproject.src.main.home.data.FoodTypeData
import retrofit2.Call
import retrofit2.Response
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class AddressBySearchActivity : BaseActivity<ActivityAddressBySearchBinding>(ActivityAddressBySearchBinding::inflate) {

    private val kakaoApi = KakaoApiRetrofitClient.apiService
    var result:String = ""
    private var addresssDatas = mutableListOf<SearchAddressData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //getHashKey()

        //주소 -> 좌표 by Geocoder
//        thread(start = true){
//            AddressToLatLng()
//        }

    }

    private fun getHashKey() {
        var packageInfo: PackageInfo? = null
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        if (packageInfo == null) Log.e("KeyHash", "KeyHash:null")
        for (signature in packageInfo!!.signatures) {
            try {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            } catch (e: NoSuchAlgorithmException) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=$signature", e)
            }
        }
    }

    fun searchAddressByKakao(address:String){
        val kakao = MutableLiveData<RoadAddress>()

        kakaoApi.getKakaoAddress(KakaoApi.API_KEY, address = address)
            .enqueue(object :retrofit2.Callback<RoadAddress>{

                override fun onResponse(call: Call<RoadAddress>, response: Response<RoadAddress>) {
                    kakao.value = response.body()

                    if(kakao.value != null){
                        for (i in 0 until 20){
                            Log.i("도로명 주소 검색 값: ", kakao.value!!.documents[i].road_address_name)
                            Log.i("주소 검색 값: ", kakao.value!!.documents[i].address_name)

                            addresssDatas.apply {
                                add(SearchAddressData(
                                    searchRoadAddress = kakao.value!!.documents[i].road_address_name,
                                    searchAddress = kakao.value!!.documents[i].address_name) )
                            }
                        }
                        //result = kakao.value!!.documents[0].road_address_name
                    }
                    else {
                        //원래는 이미지뷰로 처리
                        showCustomToast("검색 결과가 없습니다. 지번, 도로명, 건물명을 입력해주세요.")
                    }
                }

                override fun onFailure(call: Call<RoadAddress>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    fun AddressToLatLng(){
        //var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if(ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            showCustomToast("위치 권한을 설정해주세요")
        }else{
            val geocoder = Geocoder(this)
            val latlng = geocoder.getFromLocationName("서울특별시 영등포구 문래로 137 (문래동금호어울림아파트)",1)
            Log.d("TAG ", "AddressToLatLng 결과: 위도) " + latlng[0].latitude + ", 경도) " + latlng[0].longitude)
        }
    }

    override fun onResume() {
        super.onResume()

        var keyword = ""

        binding.edtAddressSearch.setOnEditorActionListener { v, actionId, event ->
            var handled = false

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                keyword = binding.edtAddressSearch.text.toString()
                Log.d("TAG ", "keyword 값:" + keyword)
                handled = true
            }
            handled
        }

        val searchAdapter = SearchAddressAdapter(this)
        binding.rvAddressSearch.adapter = searchAdapter
        searchAddressByKakao(keyword)

        searchAdapter.datas = addresssDatas

    }

}