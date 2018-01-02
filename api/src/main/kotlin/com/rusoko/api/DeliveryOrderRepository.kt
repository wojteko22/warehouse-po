package com.rusoko.api

import com.rusoko.api.dto.CommodityDto
import com.rusoko.api.dto.DeliveryOrderDetailDto
import com.rusoko.api.dto.DeliveryOrderDto
import com.rusoko.api.dto.DifferenceReportDto

interface DeliveryOrderRepository {
    val all: Collection<DeliveryOrderDto>

    operator fun get(id: Int): DeliveryOrderDetailDto

    fun createDefaultDifferenceReport(deliveryOrderId: Int)

    fun differenceReport(deliveryOrderId: Int): DifferenceReportDto

    fun availableCommodities(deliveryOrderId: Int): Collection<CommodityDto>
}