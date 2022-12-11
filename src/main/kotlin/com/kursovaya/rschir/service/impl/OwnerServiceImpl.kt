package com.kursovaya.rschir.service.impl

import com.kursovaya.rschir.dto.ContractDto
import com.kursovaya.rschir.dto.OwnerDto
import com.kursovaya.rschir.entity.ContractEntity
import com.kursovaya.rschir.entity.OwnerEntity
import com.kursovaya.rschir.exception.OwnerNotFoundException
import com.kursovaya.rschir.repository.ContractRepository
import com.kursovaya.rschir.repository.OwnerRepository
import com.kursovaya.rschir.service.OwnerService
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OwnerServiceImpl (
        private val ownerRepository: OwnerRepository,
        private val contractRepository: ContractRepository
        ) : OwnerService {

    override fun getAll(): List<OwnerDto> {
        return ownerRepository.findAll().map { it.toDto() }
    }

    override fun getById(id: Int): OwnerDto {
        return ownerRepository.findByIdOrNull(id)
            ?.toDto()
            ?: throw OwnerNotFoundException(id)
    }

    override fun search(prefix: String): List<OwnerDto> {
        return ownerRepository.findByNameStartsWithIgnoreCaseOrderByName(prefix).map { it.toDto() }
    }

    override fun findContracts(name: String): List<ContractDto> {
        val owner = ownerRepository.findByName(name)
        return contractRepository.findAllByOwner(owner).map { it.toDto() }
    }

    @Transactional
    override fun create(dto: OwnerDto): Int {
        val ownerEntity = ownerRepository.save(dto.toEntity())
        val contracts = dto.contracts.map { it.toEntity(ownerEntity) }
        contractRepository.saveAll(contracts)
        return ownerEntity.owner_id
    }

    @Transactional
    override fun update(id: Int, dto: OwnerDto) {
        var existingOwner = ownerRepository.findByIdOrNull(id)
            ?: throw OwnerNotFoundException(id)

        existingOwner.name = dto.name
        existingOwner.email = dto.email

        existingOwner = ownerRepository.save(existingOwner)
        val contracts = dto.contracts.map { it.toEntity(existingOwner) }
        contractRepository.deleteAllByOwner(existingOwner)
        contractRepository.saveAll(contracts)
    }

    @Transactional
    override fun delete(id: Int) {
        val existingOwner = ownerRepository.findByIdOrNull(id)
            ?: throw OwnerNotFoundException(id)

        contractRepository.deleteAllByOwner(existingOwner)
        ownerRepository.deleteById(existingOwner.owner_id)
    }

    private fun OwnerEntity.toDto(): OwnerDto =
        OwnerDto(
            owner_id = this.owner_id,
            name = this.name,
            email = this.email,
            contracts = this.contracts.map { it.toDto() }
        )

    private fun ContractEntity.toDto(): ContractDto =
        ContractDto(
            contract_id = this.contract_id,
            subject = this.subject,
            price = this.price,
            casee = this.casee,
            owner_id = this.owner.owner_id
        )

    private fun OwnerDto.toEntity(): OwnerEntity =
        OwnerEntity(
            owner_id = 0,
            name = this.name,
            email = this.email,
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