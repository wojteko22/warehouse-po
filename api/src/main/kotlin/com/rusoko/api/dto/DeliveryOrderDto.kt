package com.rusoko.api.dto

data class DeliveryOrderDto(val id: String, val orderNumber: String, val predictedDeliveryDate: String, val provider: String)