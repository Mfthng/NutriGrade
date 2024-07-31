package com.miftah.nutrigrade.core.remote.api

import com.miftah.nutrigrade.core.remote.dto.ScanResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AppAPI {
    @Multipart
    @POST("scan")
    suspend fun scanImage(@Part image: MultipartBody.Part): ScanResponse
}