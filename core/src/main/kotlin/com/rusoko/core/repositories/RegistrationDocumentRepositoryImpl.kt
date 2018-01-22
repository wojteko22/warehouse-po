package com.rusoko.core.repositories

import com.rusoko.api.RegistrationDocumentRepository
import com.rusoko.core.db.delivery.AcceptanceOrder
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class RegistrationDocumentRepositoryImpl : RegistrationDocumentRepository {

    override fun generate(acceptanceOrderId: Int) {
        println("Document for acceptanceOrder with id $acceptanceOrderId generated")
        transaction { AcceptanceOrder[acceptanceOrderId].handled = true }
    }
}
