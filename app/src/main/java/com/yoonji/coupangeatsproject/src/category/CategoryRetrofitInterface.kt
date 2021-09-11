package com.yoonji.coupangeatsproject.src.category

import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import com.yoonji.coupangeatsproject.src.category.models.CategoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryRetrofitInterface {
    @GET("/app/users/{userIdx}/categories/{categoryIdx}")
    fun getCategory(@Path("userIdx") userIdx:Int, @Path("categoryIdx") categoryIdx:Int ) : Call<CategoryResponse>
}