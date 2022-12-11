package com.kursovaya.rschir.service

import com.kursovaya.rschir.dto.ContractDto
import com.kursovaya.rschir.dto.OwnerDto

interface OwnerService {
    fun getAll(): List<OwnerDto>

    fun getById(id: Int): OwnerDto

    fun search(prefix: String): List<OwnerDto>

    fun findContracts(name: String): List<ContractDto>

    fun create(dto: OwnerDto): Int

    fun update(id: Int, dto: OwnerDto)

    fun delete(id: Int)
}