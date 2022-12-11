package com.kursovaya.rschir.service.impl

import com.kursovaya.rschir.dto.ContractDto
import com.kursovaya.rschir.entity.ContractEntity
import com.kursovaya.rschir.entity.OwnerEntity
import com.kursovaya.rschir.exception.ContractNotFoundException
import com.kursovaya.rschir.exception.OwnerNotFoundException
import com.kursovaya.rschir.repository.ContractRepository
import com.kursovaya.rschir.repository.OwnerRepository
import com.kursovaya.rschir.service.ContractService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ContractServiceImpl (
    private val ownerRepository: OwnerRepository,
    private val contractRepository: ContractRepository
        ) : ContractService {
    override fun getAll(): List<ContractDto> {
        return contractRepository.findAll().map { it.toDto() }
    }

    override fun getById(id: Int): ContractDto {
        return contractRepository.findByIdOrNull(id)
            ?.toDto()
            ?: throw ContractNotFoundException(id)
    }

    override fun create(owner_id: Int, dto: ContractDto): Int {
        val existingOwner = ownerRepository.findByIdOrNull(owner_id)
            ?: throw OwnerNotFoundException(owner_id)
        dto.owner_id = existingOwner.owner_id
        return contractRepository.save(dto.toEntity(existingOwner)).contract_id
    }

    override fun update(id: Int, dto: ContractDto) {
        var existingContract = contractRepository.findByIdOrNull(id)
            ?: throw ContractNotFoundException(id)

        existingContract.subject = dto.subject
        existingContract.price = dto.price
        existingContract.casee = dto.casee

        contractRepository.save(existingContract)
    }

    override fun delete(id: Int) {
        val existingContract = contractRepository.findByIdOrNull(id)
            ?: throw ContractNotFoundException(id)

        contractRepository.deleteById(existingContract.contract_id)
    }

    private fun ContractEntity.toDto(): ContractDto =
        ContractDto(
            contract_id = this.contract_id,
            subject = this.subject,
            price = this.price,
            casee = this.casee,
            owner_id = this.owner.owner_id
        )

    private fun ContractDto.toEntity(owner: OwnerEntity): ContractEntity =
        ContractEntity(
            contract_id = 0,
            subject = this.subject,
            price = this.price,
            casee = this.casee,
            owner = owner
        )
}