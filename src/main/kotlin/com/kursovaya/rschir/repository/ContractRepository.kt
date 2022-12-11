package com.kursovaya.rschir.repository

import com.kursovaya.rschir.dto.ContractDto
import com.kursovaya.rschir.entity.ContractEntity
import com.kursovaya.rschir.entity.OwnerEntity
import org.springframework.data.repository.CrudRepository

interface ContractRepository: CrudRepository<ContractEntity, Int> {

    fun deleteAllByOwner(owner: OwnerEntity)

    fun findAllByOwner(owner: OwnerEntity): List<ContractEntity>
}