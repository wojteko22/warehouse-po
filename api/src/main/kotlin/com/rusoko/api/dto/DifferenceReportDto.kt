package com.rusoko.api.dto

data class DifferenceReportDto(
        val deliveryOrderNumber: String,
        val differenceReportPositions: Collection<DifferenceReportPositionDto>
)