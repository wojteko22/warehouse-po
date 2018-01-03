package com.rusoko.core.db

import com.rusoko.api.dto.DifferenceReportDto
import com.rusoko.core.connect
import com.rusoko.core.new
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.SchemaUtils

fun main(args: Array<String>) { // Needs Providers
    connect {
        SchemaUtils.new(DifferenceReports)
    }
}

class DifferenceReport(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DifferenceReport>(DifferenceReports)

    var deliveryOrder by DeliveryOrder referencedOn DifferenceReports.deliveryOrder
    var ready by DifferenceReports.ready

    private val positions by DifferenceReportPosition referrersOn DifferenceReportPositions.differenceReport
    val availableCommodities = Commodity.all() - positions.map { it.commodity }

    fun toDto() = DifferenceReportDto(deliveryOrder.orderNumber, positions.map { it.toDto() })
}

object DifferenceReports : IntIdTable("difference_reports") {
    val deliveryOrder = reference("delivery_order", DeliveryOrders)
    val ready = bool("ready").default(false)
}