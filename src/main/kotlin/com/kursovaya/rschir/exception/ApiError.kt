package com.kursovaya.rschir.exception

data class ApiError(
    val errorCode: String,
    val description: String
)
