package com.rusoko.core.repositories

import com.rusoko.api.DifferenceReportPositionRepository
import com.rusoko.core.connect
import com.rusoko.core.db.DifferenceReportPosition
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class DifferenceReportPositionRepositoryImpl : DifferenceReportPositionRepository {

    override fun savePosition(positionId: Int, deliveredQuantity: BigDecimal) {
        connect {
            DifferenceReportPosition[positionId].deliveredQuantity = deliveredQuantity
        }
    }
}
