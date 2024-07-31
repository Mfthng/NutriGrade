package com.miftah.nutrigrade.domain

import com.miftah.nutrigrade.core.local.room.AppDB
import com.miftah.nutrigrade.core.remote.api.AppAPI
import com.miftah.nutrigrade.utils.UiState
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import java.util.concurrent.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    val api: AppAPI,
    val db: AppDB
) {
    fun scanImage(file: File) = flow {
        emit(UiState.Loading)
        try {
            val request = file.asRequestBody("image/*".toMediaTypeOrNull())
            val photo = MultipartBody.Part.createFormData("image", file.name, request)
            val result = api.scanImage(photo)
            if (result.message == "error") {
                emit(UiState.Error(result.message))
            } else {
                emit(UiState.Success(result.toScanned()))
            }
        } catch (e: HttpException) {
            emit(UiState.Error(e.message()))
        }
    }

    suspend fun saveData(data : Scanned) = db.scanDao().insertData(data.toScannedEntity())

    fun getData() = db.scanDao().getAll()

    fun getDataById(id : Int) = db.scanDao().getDataById(id)
}