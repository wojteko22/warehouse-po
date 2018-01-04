package com.rusoko.core.db.delivery

import com.rusoko.core.connect
import com.rusoko.core.new
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.SchemaUtils

fun main(args: Array<String>) {
    connect {
        SchemaUtils.new(AcceptanceOrders)
    }
}

object AcceptanceOrders : IntIdTable("acceptance_orders") {
    val differenceReport = reference("difference_report", DifferenceReports)
}