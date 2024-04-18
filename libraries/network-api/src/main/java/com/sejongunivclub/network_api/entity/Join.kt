package com.sejongunivclub.network_api.entity

data class Join(
    val studentId:String,
    val password:String,
    val position:String,
    val foot:String,
    val sex:String,
    val age:Int,
    val height:Int
)

