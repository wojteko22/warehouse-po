package com.rusoko.core.db.delivery

import com.rusoko.core.connect
import com.rusoko.core.db.InitializableTable
import com.rusoko.core.new
import org.jetbrains.exposed.sql.SchemaUtils

fun main(args: Array<String>) {
    connect {
        AcceptanceOrders.init()
    }
}

object AcceptanceOrders : InitializableTable("acceptance_orders") {
    val differenceReport = reference("difference_report", DifferenceReports)

    override fun init() {
        SchemaUtils.new(this)
    }
}