package com.rusoko.core.db

import com.rusoko.api.dto.DifferenceReportPositionDto
import com.rusoko.core.connect
import com.rusoko.core.new
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.SchemaUtils
import java.math.BigDecimal

fun main(args: Array<String>) {
    connect {
        SchemaUtils.new(DifferenceReportPositions)
    }
}

class DifferenceReportPosition(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DifferenceReportPosition>(DifferenceReportPositions)

    var commodity by Commodity referencedOn DifferenceReportPositions.commodity
    var deliveredQuantity by DifferenceReportPositions.deliveredQuantity
    private var differenceReport by DifferenceReport referencedOn DifferenceReportPositions.differenceReport

    private val orderedQuantity by lazy {
        differenceReport.deliveryOrder.positions
                .find { it.commodity.id == commodity.id }?.quantity ?: BigDecimal(0)
    }

    fun toDto() = DifferenceReportPositionDto(id.value, commodity.toDto(), orderedQuantity, deliveredQuantity)
}

object DifferenceReportPositions : IntIdTable("difference_report_positions") {
    val commodity = reference("commodity", Commodities)
    val deliveredQuantity = decimal("delivered_quantity", 13, 3).default(BigDecimal(0))
    val differenceReport = reference("difference_report", DifferenceReports)
}