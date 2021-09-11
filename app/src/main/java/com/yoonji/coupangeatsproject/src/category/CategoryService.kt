package com.yoonji.coupangeatsproject.src.category

import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.src.category.models.CategoryResponse
import com.yoonji.coupangeatsproject.src.main.home.HomeFragmentView
import com.yoonji.coupangeatsproject.src.main.home.HomeRetrofitInterface
import com.yoonji.coupangeatsproject.src.main.home.models.MainResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryService(val view: CategoryActivityView) {

    fun getCategory(userIdx :Int, categoryIdx :Int) {
        val categoryRetrofitInterface = ApplicationClass.sRetrofit.create(CategoryRetrofitInterface::class.java)

        categoryRetrofitInterface.getCategory(userIdx,categoryIdx).enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                view.onGetCategorySuccess(response.body() as CategoryResponse)
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                view.onGetCategoryFailure(t.message ?: "통신오류")
            }

        })

    }
}