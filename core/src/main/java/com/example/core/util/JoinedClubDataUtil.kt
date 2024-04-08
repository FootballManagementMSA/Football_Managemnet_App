package com.example.core.util

import com.example.core.model.JoinedClubData

object JoinedClubDataUtil {
    private var joinedClubDataList: List<JoinedClubData> = emptyList()

    fun setClubDataList(dataList: List<JoinedClubData>) {
        joinedClubDataList = dataList
    }

    fun getClubDataList(): List<JoinedClubData> = joinedClubDataList
}