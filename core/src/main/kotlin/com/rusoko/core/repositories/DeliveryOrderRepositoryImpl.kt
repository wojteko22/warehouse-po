package com.rusoko.core.repositories

import com.rusoko.api.DeliveryOrderRepository
import com.rusoko.api.dto.DeliveryOrderDto
import com.rusoko.core.connect
import com.rusoko.core.db.delivery.DeliveryOrder
import org.springframework.stereotype.Component

@Component
class DeliveryOrderRepositoryImpl : DeliveryOrderRepository {

    override val all: Collection<DeliveryOrderDto>
        get() = connect {
            DeliveryOrder.all().map { it.toDto() }
        }

    override fun get(id: Int) = connect {
        DeliveryOrder[id].toDetailDto()
    }
}
