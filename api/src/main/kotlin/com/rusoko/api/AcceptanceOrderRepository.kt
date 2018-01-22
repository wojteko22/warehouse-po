package com.rusoko.api

import com.rusoko.api.dto.AcceptanceOrderDetailDto
import com.rusoko.api.dto.AcceptanceOrderDto

/**
 * Repository of acceptance order allowing to cooperate whit orders in data base
 */
interface AcceptanceOrderRepository {
    /**
     * @property unhandled collections of orders that need to be served
     */
    val unhandled: Collection<AcceptanceOrderDto>

    /**
     * Get details about specified acceptance order
     * @param id acceptance order id
     * @return details about specified acceptance order
     */
    operator fun get(id: Int): AcceptanceOrderDetailDto

    fun confirm(id: Int)
}