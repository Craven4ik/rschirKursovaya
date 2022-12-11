package com.kursovaya.rschir.exception

import org.springframework.http.HttpStatus

class ContractNotFoundException (contractId: Int): BaseException(
    HttpStatus.NOT_FOUND,
    ApiError(
        errorCode = "contract.not.found",
        description = "Contract not found with id=$contractId"
    )
) {
}