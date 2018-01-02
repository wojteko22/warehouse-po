package com.rusoko.api.dto

import java.math.BigDecimal

data class DifferenceReportPositionDto(
        val commodity: CommodityDto,
        val orderedQuantity: BigDecimal,
        val deliveredQuantity: BigDecimal
)