package com.rusoko.core.repositories

import com.rusoko.api.AcceptanceOrderRepository
import com.rusoko.api.dto.AcceptanceOrderDetailDto
import com.rusoko.api.dto.AcceptanceOrderDto
import com.rusoko.core.connect
import com.rusoko.core.db.delivery.AcceptanceOrder
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class AcceptanceOrderRepositoryImpl : AcceptanceOrderRepository {

    override val unhandled: Collection<AcceptanceOrderDto>
        get() = connect {
            AcceptanceOrder.all().filter { !it.handled }.map { it.toDto() }
        }

    override fun get(id: Int): AcceptanceOrderDetailDto = transaction {
        AcceptanceOrder[id].toDetailDto()
    }
}
