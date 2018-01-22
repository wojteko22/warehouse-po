package com.rusoko.api.dto

data class AcceptanceOrderDetailDto(val acceptAll: Boolean, val positions: Collection<AcceptanceOrderPositionDto>)