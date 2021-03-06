package com.rusoko.core.db.delivery

import com.rusoko.api.dto.DifferenceReportDto
import com.rusoko.core.connect
import com.rusoko.core.db.InitializableTable
import com.rusoko.core.new
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.SchemaUtils

fun main(args: Array<String>) {
    connect {
        DifferenceReports.init()
    }
}

class DifferenceReport(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DifferenceReport>(DifferenceReports)

    var deliveryOrder by DeliveryOrder referencedOn DifferenceReports.deliveryOrder
    var ready by DifferenceReports.ready

    val positions by DifferenceReportPosition referrersOn DifferenceReportPositions.differenceReport
    val availableCommodities = Commodity.all() - positions.map { it.commodity }

    fun toDto() = DifferenceReportDto(deliveryOrder.orderNumber, positions.map { it.toDto() })
}

object DifferenceReports : InitializableTable("difference_reports") {
    val deliveryOrder = reference("delivery_order", DeliveryOrders)
    val ready = bool("ready").default(false)

    override fun init() {
        SchemaUtils.new(this)
    }
}