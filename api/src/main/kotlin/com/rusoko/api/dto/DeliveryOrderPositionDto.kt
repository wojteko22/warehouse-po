package com.rusoko.api.dto

import java.math.BigDecimal

data class DeliveryOrderPositionDto(
        val commodity: CommodityDto,
        val quantity: BigDecimal
)