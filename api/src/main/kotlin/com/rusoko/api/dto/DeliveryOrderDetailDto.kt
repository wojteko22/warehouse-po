package com.rusoko.api.dto

data class DeliveryOrderDetailDto(
        val orderNumber: String,
        val provider: String,
        val positions: Collection<DeliveryOrderPositionDto>
)