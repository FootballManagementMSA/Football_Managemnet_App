package com.example.core.model

data class MainHomeStudentDataUiModel(
    val status: Int,
    val code: String,
    val message: String,
    val data: StudentUiModel
)

data class StudentUiModel(
    val name: String,
    val game: Int,
    val goal: Int,
    var position: String,
    var foot: String,
    val image: String,
    val age: Int
)
