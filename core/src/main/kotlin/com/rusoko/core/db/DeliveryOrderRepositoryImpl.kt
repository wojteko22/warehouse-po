package com.rusoko.core.db

import com.rusoko.api.DeliveryOrderDto
import com.rusoko.api.DeliveryOrderRepository
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.drop
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import org.springframework.stereotype.Component

fun main(args: Array<String>) {
    connect {
        drop(DeliveryOrders)
        create(DeliveryOrders)

        DeliveryOrders.insert {
            it[orderNumber] = "St. Petersburg"
        }
    }
}

@Component
class DeliveryOrderRepositoryImpl : DeliveryOrderRepository {
    override val all: Collection<DeliveryOrderDto>
        get() = connect {
            DeliveryOrder.all().map { it.toDto() }
        }

}

class DeliveryOrder(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DeliveryOrder>(DeliveryOrders)

    private var orderNumber by DeliveryOrders.orderNumber
    private var predictedDeliveryDate by DeliveryOrders.predictedDeliveryDate

    fun toDto() = DeliveryOrderDto(orderNumber, predictedDeliveryDate.toLocalDate().toString())
}

object DeliveryOrders : IntIdTable() {
    val orderNumber = varchar("order_number", 255)
    val orderDate = date("order_date").default(DateTime.now())
    val predictedDeliveryDate = date("predicted_delivery_date").default(DateTime.now().plusDays(5))
}

fun <T> connect(statement: Transaction.() -> T): T {
    Database.connect("jdbc:mysql://localhost/warehouse", driver = "com.mysql.jdbc.Driver", user = "root")
    return transaction(statement)
}