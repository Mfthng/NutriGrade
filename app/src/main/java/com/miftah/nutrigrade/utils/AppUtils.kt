package com.miftah.nutrigrade.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AppUtils {
    fun fromListToString(list : List<String?>) : String {
        return if(!list.isNullOrEmpty()){
            Gson().toJson(list)
        } else {
            ""
        }
    }

    fun fromStringToList(value : String?) : List<String> {
        return if (!value.isNullOrEmpty()) {
            val listType = object : TypeToken<List<String>>(){}.type
            Gson().fromJson(value, listType)
        } else {
            emptyList()
        }
    }
}