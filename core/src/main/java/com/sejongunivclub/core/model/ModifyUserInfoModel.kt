package com.sejongunivclub.core.model

import java.io.File

data class ModifyUserInfoModel(
    val name: String,
    val age: Int,
    val height: Int,
    val sex: String,
    val position: String,
    val foot: String,
    val image: File
)