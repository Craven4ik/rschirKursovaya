package com.kursovaya.rschir.exception

import org.springframework.http.HttpStatus

class OwnerNotFoundException (ownerId: Int): BaseException(
    HttpStatus.NOT_FOUND,
    ApiError(
        errorCode = "owner.not.found",
        description = "Owner not found with id=$ownerId"
    )
) {
}