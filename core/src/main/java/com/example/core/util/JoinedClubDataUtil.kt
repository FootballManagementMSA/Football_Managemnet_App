package com.example.core.util

import com.example.network_api.response.JoinedClubDataResponse

object JoinedClubDataUtil {
    private var joinedClubDataList: List<JoinedClubDataResponse> = emptyList()

    fun setClubDataList(dataList: List<JoinedClubDataResponse>) {
        joinedClubDataList = dataList
    }

    fun getClubDataList(): List<JoinedClubDataResponse> = joinedClubDataList
}