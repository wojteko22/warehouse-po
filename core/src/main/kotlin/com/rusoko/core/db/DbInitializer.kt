package com.rusoko.core.db

import com.rusoko.api.DataInitializer
import com.rusoko.core.connect
import com.rusoko.core.db.delivery.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.springframework.stereotype.Component

fun main(args: Array<String>) {
    DbInitializer().init()
}

@Component
class DbInitializer : DataInitializer {

    override fun init() {
        val deliveryTables = arrayOf(AcceptanceOrderPositions, AcceptanceOrders, DifferenceReportPositions,
                DifferenceReports, DeliveryOrderPositions, DeliveryOrders, Commodities, Producers, Measures, Providers)
        connect {
            SchemaUtils.drop(*deliveryTables)
            deliveryTables.reversedArray().forEach { it.init() }
        }
    }
}
