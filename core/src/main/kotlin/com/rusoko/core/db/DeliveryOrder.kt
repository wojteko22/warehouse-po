package com.rusoko.core.db

import com.rusoko.api.dto.DeliveryOrderDetailDto
import com.rusoko.api.dto.DeliveryOrderDto
import com.rusoko.core.connect
import com.rusoko.core.new
import com.rusoko.core.nextNumber
import com.rusoko.core.random
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.joda.time.DateTime

fun main(args: Array<String>) { // Needs Providers
    connect {
        SchemaUtils.new(DeliveryOrders)

        repeat(10) {
            DeliveryOrders.insert {
                it[orderNumber] = "D-" + random.nextNumber(4)
                it[orderDate] = DateTime.now().minusDays(random.nextInt(30))
                it[predictedDeliveryDate] = DateTime.now().plusDays(random.nextInt(10))
                it[provider] = Provider.random().id
            }
        }
    }
}

class DeliveryOrder(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DeliveryOrder>(DeliveryOrders)

    var orderNumber by DeliveryOrders.orderNumber
    private var predictedDeliveryDate by DeliveryOrders.predictedDeliveryDate
    private var provider by Provider referencedOn DeliveryOrders.provider

    val positions by DeliveryOrderPosition referrersOn DeliveryOrderPositions.deliveryOrder

    fun toDto() = DeliveryOrderDto(orderNumber, predictedDeliveryDate.toLocalDate().toString(), provider.name)
    fun toDetailDto() = DeliveryOrderDetailDto(orderNumber, provider.name, positions.map { it.toDto() })
}

object DeliveryOrders : IntIdTable("delivery_orders") {
    val orderNumber = varchar("order_number", 255).uniqueIndex()
    val orderDate = date("order_date")
    val predictedDeliveryDate = date("predicted_delivery_date")
    val provider = reference("provider", Providers)
}