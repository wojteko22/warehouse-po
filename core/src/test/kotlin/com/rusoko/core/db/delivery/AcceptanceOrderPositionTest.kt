package com.rusoko.core.db.delivery

import com.rusoko.core.connect
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class AcceptanceOrderPositionTest {

    @Before
    fun init() {
        Database.connect("jdbc:h2:~/Desktop/warehouse-test", driver = "org.h2.Driver")
        val deliveryTables = arrayOf(AcceptanceOrderPositions, AcceptanceOrders, DifferenceReportPositions,
                DifferenceReports, DeliveryOrderPositions, DeliveryOrders, Commodities, Producers, Measures, Providers)
        connect {
            SchemaUtils.drop(*deliveryTables)
            deliveryTables.reversedArray().forEach { it.init() }
        }
    }

    @Test
    fun `status should be -1 if there is nothing to accept`() {
        transaction {
            val id = generateAcceptanceOrder { BigDecimal(0) }
            val status = acceptanceOrderStatus(id)
            assertThat(status).isEqualTo(-1)
        }
    }

    @Test
    fun `status should be 1 if there is all to accept`() {
        transaction {
            val id = generateAcceptanceOrder { position -> position.deliveredQuantity }
            val status = acceptanceOrderStatus(id)
            assertThat(status).isEqualTo(1)
        }
    }

    @Test
    fun `status should be 0 if there is something to accept but not all`() {
        transaction {
            val id = generateAcceptanceOrder { position -> position.deliveredQuantity / BigDecimal(2)}
            val status = acceptanceOrderStatus(id)
            assertThat(status).isEqualTo(0)
        }
    }

    private fun acceptanceOrderStatus(id: Int): Int {
        val order = AcceptanceOrder[id]
        val dto = order.toDetailDto()
        return dto.status
    }
}