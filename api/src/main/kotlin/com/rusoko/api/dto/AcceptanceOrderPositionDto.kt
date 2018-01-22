package com.rusoko.api.dto

import java.math.BigDecimal

data class AcceptanceOrderPositionDto(
        val commodityDto: CommodityDto,
        val quantityToAccept: BigDecimal,
        val fraction: BigDecimal
)