package com.rusoko.core.db.delivery

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

    private val deliveryOrder = differenceReport.deliveryOrder

    fun toDto() = AcceptanceOrderDto(id.value, deliveryOrder.orderNumber, deliveryOrder.provider.name)
}

object AcceptanceOrders : InitializableTable("acceptance_orders") {
    val differenceReport = reference("difference_report", DifferenceReports)

    override fun init() {
        SchemaUtils.new(this)
    }
}