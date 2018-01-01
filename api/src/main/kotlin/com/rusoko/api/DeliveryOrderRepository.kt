package com.rusoko.api

interface DeliveryOrderRepository {
    val all: Collection<DeliveryOrderDto>
}