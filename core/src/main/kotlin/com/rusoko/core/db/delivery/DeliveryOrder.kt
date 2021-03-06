package com.rusoko.core.db.delivery

import com.rusoko.api.dto.DeliveryOrderDetailDto
import com.rusoko.api.dto.DeliveryOrderDto
import com.rusoko.core.connect
import com.rusoko.core.db.InitializableTable
import com.rusoko.core.new
import com.rusoko.core.nextNumber
import com.rusoko.core.random
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insertAndGetId
import org.joda.time.DateTime

fun main(args: Array<String>) { // Needs Providers
    connect {
        DeliveryOrders.init()
    }
}

class DeliveryOrder(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DeliveryOrder>(DeliveryOrders)

    var orderNumber by DeliveryOrders.orderNumber
    private var predictedDeliveryDate by DeliveryOrders.predictedDeliveryDate
    var provider by Provider referencedOn DeliveryOrders.provider

    val positions by DeliveryOrderPosition referrersOn DeliveryOrderPositions.deliveryOrder
    private val differenceReports by DifferenceReport referrersOn DifferenceReports.deliveryOrder
    val verified get() = differenceReports.filter { it.ready }.size == 1

    fun toDto() = DeliveryOrderDto(id.toString(), orderNumber, predictedDeliveryDate.toLocalDate().toString(), provider.name)
    fun toDetailDto() = DeliveryOrderDetailDto(orderNumber, provider.name, positions.map { it.toDto() })
}

object DeliveryOrders : InitializableTable("delivery_orders") {
    val orderNumber = varchar("order_number", 255).uniqueIndex()
    val orderDate = date("order_date")
    val predictedDeliveryDate = date("predicted_delivery_date")
    val provider = reference("provider", Providers)

    override fun init() {
        SchemaUtils.new(this)

        repeat(10) {
            insertAndGetId()
        }
    }

    fun insertAndGetId(): EntityID<Int> = insertAndGetId {
        it[orderNumber] = "D-" + random.nextNumber(4)
        it[orderDate] = DateTime.now().minusDays(random.nextInt(30))
        it[predictedDeliveryDate] = DateTime.now().plusDays(random.nextInt(10))
        it[provider] = Provider.random().id
    } ?: throw RuntimeException("Error during inserting")
}