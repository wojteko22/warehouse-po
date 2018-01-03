package com.rusoko.api

import java.math.BigDecimal

interface DifferenceReportPositionRepository {

    fun savePosition(positionId: Int, deliveredQuantity: BigDecimal)
}