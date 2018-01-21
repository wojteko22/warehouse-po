package com.rusoko.api

import com.rusoko.api.dto.DeliveryOrderDetailDto
import com.rusoko.api.dto.DeliveryOrderDto

interface DeliveryOrderRepository {
    val unverified: Collection<DeliveryOrderDto>

    operator fun get(orderId: Int): DeliveryOrderDetailDto
}