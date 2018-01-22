package com.rusoko.api

import java.math.BigDecimal

/**
 * Repository of difference report positions allowing to cooperate with data base
 */
interface DifferenceReportPositionRepository {

    /**
     * Save delivered quantity of commodity from report
     * @param positionId id of position from report
     * @param deliveredQuantity delivered quantity of the commodity
     */
    fun savePosition(positionId: Int, deliveredQuantity: BigDecimal)
}