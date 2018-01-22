package com.rusoko.core.db.delivery

import com.rusoko.api.dto.AcceptanceOrderDetailDto
import com.rusoko.api.dto.AcceptanceOrderDto
import com.rusoko.core.connect
import com.rusoko.core.db.InitializableTable
import com.rusoko.core.new
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.SchemaUtils

fun main(args: Array<String>) {
    connect {
        AcceptanceOrders.init()
    }
}

class AcceptanceOrder(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AcceptanceOrder>(AcceptanceOrders)

    var differenceReport by DifferenceReport referencedOn AcceptanceOrders.differenceReport

    private val positions by AcceptanceOrderPosition referrersOn AcceptanceOrderPositions.acceptanceOrder
    private val deliveryOrder get() = differenceReport.deliveryOrder
    private val acceptAll = positions.all { it.fraction.toDouble() == 1.0 }
    private val acceptNone = positions.all { it.fraction.toDouble() == 0.0 }

    fun toDto() = AcceptanceOrderDto(id.value, deliveryOrder.orderNumber, deliveryOrder.provider.name)
    fun toDetailDto() = AcceptanceOrderDetailDto(acceptAll, positions.map { it.toDto() })
}

object AcceptanceOrders : InitializableTable("acceptance_orders") {
    val differenceReport = reference("difference_report", DifferenceReports)

    override fun init() {
        SchemaUtils.new(this)

    }
}