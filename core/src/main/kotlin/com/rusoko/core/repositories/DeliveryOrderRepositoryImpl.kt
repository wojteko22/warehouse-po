package com.rusoko.core.repositories

import com.rusoko.api.DeliveryOrderRepository
import com.rusoko.api.dto.DeliveryOrderDto
import com.rusoko.core.connect
import com.rusoko.core.db.delivery.DeliveryOrder
import org.springframework.stereotype.Component

@Component
class DeliveryOrderRepositoryImpl : DeliveryOrderRepository {

    override val unverified: Collection<DeliveryOrderDto>
        get() = connect {
            DeliveryOrder.all().filter { !it.verified }.map { it.toDto() }
        }

    override fun get(orderId: Int) = connect {
        DeliveryOrder[orderId].toDetailDto()
    }
}
