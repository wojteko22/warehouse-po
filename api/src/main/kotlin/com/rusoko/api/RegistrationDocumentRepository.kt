package com.rusoko.api

interface RegistrationDocumentRepository {

    fun generate(acceptanceOrderId: Int)
}