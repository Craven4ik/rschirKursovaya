package com.kursovaya.rschir.controller

import com.kursovaya.rschir.dto.ContractDto
import com.kursovaya.rschir.dto.OwnerDto
import com.kursovaya.rschir.service.ContractService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contracts")
class ContractController (
    private val contractService: ContractService
        ) {
    @GetMapping
    fun getAll(): List<ContractDto> =
        contractService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): ContractDto =
        contractService.getById(id)

    @PostMapping
    fun create(@RequestParam("owner_id") owner_id: Int, @RequestBody dto: ContractDto): Int =
        contractService.create(owner_id, dto)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody dto: ContractDto) {
        contractService.update(id, dto)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) {
        contractService.delete(id)
    }
}