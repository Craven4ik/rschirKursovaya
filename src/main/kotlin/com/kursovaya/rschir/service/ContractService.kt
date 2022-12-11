package com.kursovaya.rschir.service

import com.kursovaya.rschir.dto.ContractDto

interface ContractService {
    fun getAll(): List<ContractDto>

    fun getById(id: Int): ContractDto

    fun create(owner_id: Int, dto: ContractDto): Int

    fun update(id: Int, dto: ContractDto)

    fun delete(id: Int)
}