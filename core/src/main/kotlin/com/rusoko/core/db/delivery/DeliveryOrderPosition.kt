package com.rusoko.core.db.delivery

import com.rusoko.api.dto.DeliveryOrderPositionDto
import com.rusoko.core.connect
import com.rusoko.core.db.InitializableTable
import com.rusoko.core.new
import com.rusoko.core.random
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import java.math.BigDecimal

fun main(args: Array<String>) {
    connect {
        DeliveryOrderPositions.init()
    }
}

class DeliveryOrderPosition(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DeliveryOrderPosition>(DeliveryOrderPositions)

    var commodity by Commodity referencedOn DeliveryOrderPositions.commodity
    var quantity by DeliveryOrderPositions.quantity

    fun toDto() = DeliveryOrderPositionDto(commodity.toDto(), quantity)
}

object DeliveryOrderPositions : InitializableTable("delivery_order_positions") {
    val quantity = decimal("quantity", 13, 3)
    val deliveryOrder = reference("delivery_order", DeliveryOrders)
    val commodity = reference("commodity", Commodities)

    override fun init() {
        SchemaUtils.new(this)

        repeat(10) {
            val randomQuantity = random.nextInt(1000000000).toDouble() / 1000
            insert {
                it[quantity] = BigDecimal(randomQuantity)
                it[deliveryOrder] = DeliveryOrder.random().id
                it[commodity] = Commodity.random().id
            }
        }
    }
}