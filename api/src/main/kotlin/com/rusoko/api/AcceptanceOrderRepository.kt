package com.rusoko.api

import com.rusoko.api.dto.AcceptanceOrderDto

interface AcceptanceOrderRepository {
    val unhandled: Collection<AcceptanceOrderDto>
}