package com.rusoko.core.db.delivery

import com.rusoko.api.dto.AcceptanceOrderDetailDto
import com.rusoko.api.dto.AcceptanceOrderDto
import com.rusoko.core.connect
import com.rusoko.core.db.InitializableTable
import com.rusoko.core.new
import com.rusoko.core.repositories.DifferenceReportRepositoryImpl
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert

fun main(args: Array<String>) {
    connect {
        AcceptanceOrders.init()
    }
}

class AcceptanceOrder(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AcceptanceOrder>(AcceptanceOrders)

    var differenceReport by DifferenceReport referencedOn AcceptanceOrders.differenceReport

    private val deliveryOrder get() = differenceReport.deliveryOrder

    fun toDto() = AcceptanceOrderDto(id.value, deliveryOrder.orderNumber, deliveryOrder.provider.name)
    fun toDetailDto() = AcceptanceOrderDetailDto(2) // todo:
}

object AcceptanceOrders : InitializableTable("acceptance_orders") {
    val differenceReport = reference("difference_report", DifferenceReports)

    override fun init() {
        SchemaUtils.new(this)
        val deliveryOrderId = DeliveryOrders.insertAndGetId()
        DeliveryOrderPositions.insertTo(deliveryOrderId)
        val differenceReportRepository = DifferenceReportRepositoryImpl()
        val differenceReportId = differenceReportRepository.createDefault(deliveryOrderId.value)
        differenceReportRepository.send(differenceReportId)
        AcceptanceOrders.insert {
            it[differenceReport] = EntityID(differenceReportId, DifferenceReports)
        }
        DifferenceReport[differenceReportId].positions.forEach {
            AcceptanceOrderPosition.new {
                differenceReportPosition = it
                quantityToAccept = it.deliveredQuantity
            }
        }
    }
}