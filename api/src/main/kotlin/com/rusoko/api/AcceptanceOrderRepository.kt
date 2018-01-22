package com.rusoko.api

import com.rusoko.api.dto.AcceptanceOrderDetailDto
import com.rusoko.api.dto.AcceptanceOrderDto

interface AcceptanceOrderRepository {
    val unhandled: Collection<AcceptanceOrderDto>

    operator fun get(id: Int): AcceptanceOrderDetailDto
}