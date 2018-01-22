package com.rusoko.api

import com.rusoko.api.dto.DeliveryOrderDetailDto
import com.rusoko.api.dto.DeliveryOrderDto

/**
 * Repository of delivery order allowing to cooperate with orders from data base
 */
interface DeliveryOrderRepository {
    /**
     * @property unverified collection of delivery order that are not yet verified
     */
    val unverified: Collection<DeliveryOrderDto>

    /**
     * Get details about specified delivery order
     * @param orderId id of taken order
     * @return details about specified delivery order
     */
    operator fun get(orderId: Int): DeliveryOrderDetailDto
}