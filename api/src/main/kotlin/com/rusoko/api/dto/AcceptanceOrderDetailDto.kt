package com.rusoko.api.dto

data class AcceptanceOrderDetailDto(val status: Int, val positions: Collection<AcceptanceOrderPositionDto>)