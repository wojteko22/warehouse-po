package com.rusoko.core.db.delivery

import com.rusoko.api.dto.AcceptanceOrderPositionDto
import com.rusoko.core.connect
import com.rusoko.core.db.InitializableTable
import com.rusoko.core.new
import com.rusoko.core.repositories.DifferenceReportRepositoryImpl
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import java.math.BigDecimal

fun main(args: Array<String>) {
    connect {
        AcceptanceOrderPositions.init()
    }
}

class AcceptanceOrderPosition(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AcceptanceOrderPosition>(AcceptanceOrderPositions)

    var differenceReportPosition by DifferenceReportPosition referencedOn
            AcceptanceOrderPositions.differenceReportPosition
    var quantityToAccept by AcceptanceOrderPositions.quantityToAccept

    val fraction get() = quantityToAccept / differenceReportPosition.deliveredQuantity

    fun toDto() = AcceptanceOrderPositionDto(differenceReportPosition.commodity.toDto(), quantityToAccept, fraction)
}

object AcceptanceOrderPositions : InitializableTable("acceptance_order_positions") {
    val differenceReportPosition = reference("difference_report_position", DifferenceReportPositions)
    val acceptanceOrder = reference("acceptance_order", AcceptanceOrders)
    val quantityToAccept = decimal("quantity_to_accept", 13, 3)

    override fun init() {
        SchemaUtils.new(this)
        generateAcceptanceOrder { it.deliveredQuantity }
        generateAcceptanceOrder { BigDecimal(0) }
        generateAcceptanceOrder { it.deliveredQuantity / BigDecimal(2) }
    }

    private fun generateAcceptanceOrder(amount: (DifferenceReportPosition) -> BigDecimal) {
        val deliveryOrderId = DeliveryOrders.insertAndGetId()
        DeliveryOrderPositions.insertTo(deliveryOrderId)
        val differenceReportRepository = DifferenceReportRepositoryImpl()
        val differenceReportId = differenceReportRepository.createDefault(deliveryOrderId.value)

        DifferenceReport[differenceReportId].positions.forEach {
            it.deliveredQuantity = BigDecimal(100)
        }
        differenceReportRepository.send(differenceReportId)

        val acceptanceOrderId = AcceptanceOrders.insertAndGetId {
            it[differenceReport] = EntityID(differenceReportId, DifferenceReports)
        } ?: throw RuntimeException("Error during inserting")
        DifferenceReport[differenceReportId].positions.forEach { position ->
            if (position.deliveredQuantity > BigDecimal(0))
                AcceptanceOrderPositions.insert {
                    it[differenceReportPosition] = position.id
                    it[acceptanceOrder] = acceptanceOrderId
                    it[quantityToAccept] = amount(position)
                }
        }
    }
}