package com.kursovaya.rschir.dto

data class OwnerDto(
    val owner_id: Int? = null,
    val name: String,
    val email:String,
    val contracts: List<ContractDto> = emptyList()
)
