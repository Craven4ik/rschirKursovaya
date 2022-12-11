package com.kursovaya.rschir.entity

import jakarta.persistence.*

@Entity
@Table(name = "owner")
class OwnerEntity (
    @Column(name = "name")
    var name: String = "",
    @Column(name = "email")
    var email:String = "",
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    val owner_id: Int = 0,

    @OneToMany(mappedBy = "owner")
    var contracts: List<ContractEntity> = emptyList()
        ) {
}