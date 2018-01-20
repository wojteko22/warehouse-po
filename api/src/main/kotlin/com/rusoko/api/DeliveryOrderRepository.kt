package com.rusoko.api

import com.rusoko.api.dto.DeliveryOrderDetailDto
import com.rusoko.api.dto.DeliveryOrderDto

interface DeliveryOrderRepository {
    val all: Collection<DeliveryOrderDto>

    operator fun get(orderNumber: String): DeliveryOrderDetailDto
}