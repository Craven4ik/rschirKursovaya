package com.kursovaya.rschir.dto

data class ContractDto(
    val contract_id: Int? = null,
    val subject: String,
    val price: Int,
    val casee: String,
    var owner_id: Int? = null
)
