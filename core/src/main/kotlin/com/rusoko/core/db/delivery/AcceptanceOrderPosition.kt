package com.rusoko.core.db.delivery

import com.rusoko.core.connect
import com.rusoko.core.db.InitializableTable
import com.rusoko.core.new
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.SchemaUtils

fun main(args: Array<String>) {
    connect {
        AcceptanceOrderPositions.init()
    }
}

class AcceptanceOrderPosition(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AcceptanceOrderPosition>(AcceptanceOrderPositions)

    var differenceReportPosition by DifferenceReportPosition referencedOn
            AcceptanceOrderPositions.differenceReportPosition
    var quantityToAccept by AcceptanceOrderPositions.quantityToAccept
}

object AcceptanceOrderPositions : InitializableTable("acceptance_order_positions") {
    val differenceReportPosition = reference("difference_report_position", DifferenceReportPositions)
    val quantityToAccept = decimal("quantity_to_accept", 13, 3)

    override fun init() {
        SchemaUtils.new(this)
    }
}