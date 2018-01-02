package com.rusoko.core.db

import com.rusoko.api.dto.DeliveryOrderPositionDto
import com.rusoko.core.connect
import com.rusoko.core.new
import com.rusoko.core.random
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import java.math.BigDecimal

fun main(args: Array<String>) { // Needs Providers
    connect {
        SchemaUtils.new(DeliveryOrderPositions)

        repeat(10) {
            val randomQuantity = random.nextInt(1000000000).toDouble() / 1000
            DeliveryOrderPositions.insert {
                it[quantity] = BigDecimal(randomQuantity)
                it[deliveryOrder] = DeliveryOrder.random().id
                it[commodity] = Commodity.random().id
            }
        }
    }
}

class DeliveryOrderPosition(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DeliveryOrderPosition>(DeliveryOrderPositions)

    var commodity by Commodity referencedOn DeliveryOrderPositions.commodity
    var quantity by DeliveryOrderPositions.quantity

    fun toDto() = DeliveryOrderPositionDto(commodity.toDto(), quantity)
}

object DeliveryOrderPositions : IntIdTable() {
    val quantity = decimal("quantity", 13, 3)
    val deliveryOrder = reference("delivery_order", DeliveryOrders)
    val commodity = reference("commodity", Commodities)
}