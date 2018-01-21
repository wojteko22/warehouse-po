package com.rusoko.core.repositories

import com.rusoko.api.AcceptanceOrderRepository
import com.rusoko.api.dto.AcceptanceOrderDto
import com.rusoko.core.connect
import com.rusoko.core.db.delivery.AcceptanceOrder
import org.springframework.stereotype.Component

@Component
class AcceptanceOrderRepositoryImpl : AcceptanceOrderRepository {

    override val unhandled: Collection<AcceptanceOrderDto>
        get() = connect {
            AcceptanceOrder.all().map { it.toDto() } // todo: Nieobsłużone
        }
}
