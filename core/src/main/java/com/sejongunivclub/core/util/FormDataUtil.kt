package com.sejongunivclub.core.util

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

object FormDataUtil {
    fun mapToMultipart(key: String, image: File): MultipartBody.Part {
        val imageBody = image.asRequestBody("image/*".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(key, imageBody.toString(), imageBody)
    }

    fun mapToRequestBody(key: String): RequestBody {
        return key.toRequestBody("text/plain".toMediaTypeOrNull())
    }


}