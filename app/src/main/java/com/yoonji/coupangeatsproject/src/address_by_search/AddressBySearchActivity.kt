package com.yoonji.coupangeatsproject.src.address_by_search

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityAddressBySearchBinding
import com.yoonji.coupangeatsproject.src.address_by_map.KakaoApi
import com.yoonji.coupangeatsproject.src.address_by_map.KakaoApiRetrofitClient
import com.yoonji.coupangeatsproject.src.address_by_map.data.RoadAddress
import com.yoonji.coupangeatsproject.src.restaurant.RestaurantRecyclerViewDivider
import retrofit2.Call
import retrofit2.Response
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.concurrent.thread


class AddressBySearchActivity : BaseActivity<ActivityAddressBySearchBinding>(ActivityAddressBySearchBinding::inflate) {

    private val kakaoApi = KakaoApiRetrofitClient.apiService
    var result:String = ""
    private var addresssDatas = mutableListOf<SearchAddressData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //getHashKey()


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


    override fun onResume() {
        super.onResume()

        var keyword = ""

        binding.edtAddressSearch.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {

                // 엔터키로 검색
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    //키패드 내리기
                    val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(binding.edtAddressSearch.windowToken, 0)

                    binding.tvAddrressSearchText.visibility = GONE
                    binding.tvAddrressSearchText2.visibility = GONE
                    binding.tvAddrressSearchText3.visibility = GONE
                    binding.tvAddrressSearchText4.visibility = GONE
                    binding.tvAddrressSearchText5.visibility = GONE
                    binding.tvAddrressSearchText6.visibility = GONE
                    binding.tvAddrressSearchText7.visibility = GONE

                    keyword = binding.edtAddressSearch.text.toString()
                    searchAddressByKakao(keyword)

                    return true
                }
                return false
            }
        })

    }

    fun searchAddressByKakao(address:String){
        val kakao = MutableLiveData<RoadAddress>()

        kakaoApi.getKakaoAddress(KakaoApi.API_KEY, address = address)
            .enqueue(object :retrofit2.Callback<RoadAddress>{

                override fun onResponse(call: Call<RoadAddress>, response: Response<RoadAddress>) {
                    kakao.value = response.body()

                    if(kakao.value != null){
                        for (i in 0 until 10){

                            val searchAdapter = SearchAddressAdapter(this@AddressBySearchActivity)
                            binding.rvAddressSearch.adapter = searchAdapter

                            // detail menu에 얇은 divider 추가
                            val color = applicationContext.getColor(R.color.greyForReview)
                            binding.rvAddressSearch.addItemDecoration(RestaurantRecyclerViewDivider(1f,0f, color))

                            addresssDatas.apply {
                                add(SearchAddressData(
                                    searchAddress = kakao.value!!.documents[i].place_name,
                                    searchRoadAddress = kakao.value!!.documents[i].road_address_name) )
                            }

                            searchAdapter.datas = addresssDatas
                            binding.rvAddressSearch.visibility = VISIBLE
                        }
                    }
                    else {
                        showCustomToast("검색 결과가 없습니다. 지번, 도로명, 건물명을 입력해주세요.")
                    }
                }

                override fun onFailure(call: Call<RoadAddress>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

}