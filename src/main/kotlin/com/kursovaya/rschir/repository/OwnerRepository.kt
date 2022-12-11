package com.kursovaya.rschir.repository

import com.kursovaya.rschir.entity.OwnerEntity
import org.springframework.data.repository.CrudRepository

interface OwnerRepository: CrudRepository<OwnerEntity, Int> {

    fun findByNameStartsWithIgnoreCaseOrderByName(prefix: String): List<OwnerEntity>

    fun findByName(name: String): OwnerEntity
}