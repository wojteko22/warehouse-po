package com.rusoko.core.repositories

import com.rusoko.api.DeliveryOrderRepository
import com.rusoko.api.dto.DeliveryOrderDto
import com.rusoko.core.connect
import com.rusoko.core.db.DeliveryOrder
import com.rusoko.core.db.DifferenceReport
import com.rusoko.core.db.DifferenceReportPositions
import com.rusoko.core.db.DifferenceReports
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.springframework.stereotype.Component

@Component
class DeliveryOrderRepositoryImpl : DeliveryOrderRepository {

    override val all: Collection<DeliveryOrderDto>
        get() = connect {
            DeliveryOrder.all().map { it.toDto() }
        }

    override fun get(id: Int) = connect {
        DeliveryOrder[id].toDetailDto()
    }

    override fun createDefaultDifferenceReport(deliveryOrderId: Int) {
        connect {
            val aDeliveryOrder = DeliveryOrder[deliveryOrderId]
            val differenceReportId = DifferenceReports.insertAndGetId {
                it[deliveryOrder] = aDeliveryOrder.id
            } ?: throw RuntimeException("cannot insert difference report with id $deliveryOrderId")
            aDeliveryOrder.positions.forEach { orderPosition ->
                DifferenceReportPositions.insert {
                    it[commodity] = orderPosition.commodity.id
                    it[differenceReport] = differenceReportId
                }
            }
        }
    }

    override fun differenceReport(deliveryOrderId: Int) = connect {
        findDifferenceReport(deliveryOrderId).toDto()
    }

    override fun availableCommodities(deliveryOrderId: Int) = connect {
        findDifferenceReport(deliveryOrderId).availableCommodities.map { it.toDto() }
    }

    private fun findDifferenceReport(deliveryOrderId: Int) = DifferenceReport.find {
        DifferenceReports.deliveryOrder.eq(deliveryOrderId)
    }.first()
}
