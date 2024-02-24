package com.vickbt.shared.domain.models

data class ErrorResponse(val errorCode: Int, val errorMessage: String) : Exception(errorMessage)
