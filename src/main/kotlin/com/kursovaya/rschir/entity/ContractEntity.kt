package com.kursovaya.rschir.entity

import jakarta.persistence.*

@Entity
@Table(name = "contract")
class ContractEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    val contract_id: Int = 0,
    var subject: String = "",
    var price: Int = 0,
    var casee: String = "",
    @ManyToOne
    @JoinColumn(name = "owner_id")
    var owner: OwnerEntity
        ) {
}