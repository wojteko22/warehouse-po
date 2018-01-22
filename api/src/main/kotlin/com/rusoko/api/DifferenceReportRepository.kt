package com.rusoko.api

import com.rusoko.api.dto.CommodityDto
import com.rusoko.api.dto.DifferenceReportDto

/**
 * Repository of difference reports
 */
interface DifferenceReportRepository {

    /**
     * Init default difference report for delivery order and insert it into data base
     * @param deliveryOrderId id of delivery order for which report will be created
     * @return inserted difference report id
     */
    fun createDefault(deliveryOrderId: Int): Int

    /**
     * Get specified difference report
     * @param id difference report id
     * @return difference report dto
     */
    operator fun get(id: Int): DifferenceReportDto

    /**
     * All available commodities that are not included in difference report
     * @param id id of difference report
     * @return collection off available commodities
     */
    fun availableCommodities(id: Int): Collection<CommodityDto>

    /**
     * Add new commodity to difference report
     * @param id edited difference report id
     * @param commodityId added commodity id
     */
    fun addCommodity(id: Int, commodityId: Int)

    /**
     * Send difference report do other departments, mark it as ready
     * @param id difference report id
     */
    fun send(id: Int)
}