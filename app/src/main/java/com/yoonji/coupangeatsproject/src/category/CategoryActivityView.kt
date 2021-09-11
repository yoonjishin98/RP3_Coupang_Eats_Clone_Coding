package com.yoonji.coupangeatsproject.src.category

import com.yoonji.coupangeatsproject.src.category.models.CategoryResponse
import com.yoonji.coupangeatsproject.src.main.home.models.MainResponse

interface CategoryActivityView {

    fun onGetCategorySuccess(response: CategoryResponse)
    fun onGetCategoryFailure(message: String)
}