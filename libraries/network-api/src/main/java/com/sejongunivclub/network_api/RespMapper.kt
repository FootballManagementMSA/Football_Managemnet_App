package com.sejongunivclub.network_api

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

object RespMapper {
    fun errorMapper(errorBodyJson: String): BaseResp {
        return try {
            val gson = Gson()
            val errorResponse = gson.fromJson(errorBodyJson, BaseResp::class.java)
            if (errorResponse?.message != null) {
                errorResponse
            } else {
                BaseResp(0, null, "Unknown error")
            }
        } catch (e: JsonSyntaxException) {
            BaseResp(0, null, "Error parsing error response")
        }
    }
}