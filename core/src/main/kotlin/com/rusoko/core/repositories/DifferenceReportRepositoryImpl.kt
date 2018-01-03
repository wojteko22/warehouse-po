package com.rusoko.core.repositories

import com.rusoko.api.DifferenceReportRepository
import com.rusoko.core.connect
import com.rusoko.core.db.DeliveryOrder
import com.rusoko.core.db.DifferenceReport
import com.rusoko.core.db.DifferenceReportPositions
import com.rusoko.core.db.DifferenceReports
import org.jetbrains.exposed.sql.*
import org.springframework.stereotype.Component

@Component
class DifferenceReportRepositoryImpl : DifferenceReportRepository {

    override fun createDefault(deliveryOrderId: Int): Int = connect {
        val aDeliveryOrder = DeliveryOrder[deliveryOrderId]

        val query = DifferenceReports.select {
            DifferenceReports.deliveryOrder eq aDeliveryOrder.id
        }

        if (!query.empty())
            deleteOld(query)

        val differenceReportId = DifferenceReports.insertAndGetId {
            it[deliveryOrder] = aDeliveryOrder.id
        } ?: throw RuntimeException("cannot insert difference report with id $deliveryOrderId")

        aDeliveryOrder.positions.forEach { orderPosition ->
            DifferenceReportPositions.insert {
                it[commodity] = orderPosition.commodity.id
                it[differenceReport] = differenceReportId
            }
        }

        differenceReportId.value
    }

    private fun deleteOld(query: Query) {
        val differenceReportToDelete = query.first()[DifferenceReports.id]

        DifferenceReportPositions.deleteWhere {
            DifferenceReportPositions.differenceReport eq differenceReportToDelete
        }

        DifferenceReports.deleteWhere { DifferenceReports.id eq differenceReportToDelete }
    }

    override operator fun get(id: Int) = connect {
        DifferenceReport[id].toDto()
    }

    override fun availableCommodities(id: Int) = connect {
        DifferenceReport[id].availableCommodities.map { it.toDto() }
    }
}
