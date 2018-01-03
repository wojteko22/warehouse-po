package com.rusoko.api

import com.rusoko.api.dto.CommodityDto
import com.rusoko.api.dto.DifferenceReportDto

interface DifferenceReportRepository {

    fun createDefault(deliveryOrderId: Int): Int

    operator fun get(id: Int): DifferenceReportDto

    fun availableCommodities(id: Int): Collection<CommodityDto>

    fun addCommodity(id: Int, commodityId: Int)
}