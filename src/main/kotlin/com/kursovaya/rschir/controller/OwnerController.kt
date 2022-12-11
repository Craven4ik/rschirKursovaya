package com.kursovaya.rschir.controller

import com.kursovaya.rschir.dto.ContractDto
import com.kursovaya.rschir.dto.OwnerDto
import com.kursovaya.rschir.service.OwnerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/owners")
class OwnerController (
    private val ownerService: OwnerService
        ) {
    @GetMapping
    fun getAll(): List<OwnerDto> =
        ownerService.getAll();

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): OwnerDto =
        ownerService.getById(id)

    @GetMapping("/search")
    fun searchOwners(@RequestParam("prefix") prefix: String): List<OwnerDto> =
        ownerService.search(prefix)

    @GetMapping("/contracts")
    fun findContracts(@RequestParam("name") name: String): List<ContractDto> =
        ownerService.findContracts(name)

    @PostMapping
    fun create(@RequestBody dto: OwnerDto): Int =
        ownerService.create(dto)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody dto: OwnerDto) {
        ownerService.update(id, dto)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) {
        ownerService.delete(id)
    }
}