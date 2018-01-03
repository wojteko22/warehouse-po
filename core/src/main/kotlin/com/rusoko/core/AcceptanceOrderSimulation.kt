package com.rusoko.core

import com.rusoko.core.db.AcceptanceOrderPosition
import com.rusoko.core.db.DifferenceReport

fun main(args: Array<String>) {
    connect {
        simulateOrderToAcceptAll()
    }
}

private fun simulateOrderToAcceptAll() {
    DifferenceReport[1].positions.forEach {
        AcceptanceOrderPosition.new {
            differenceReportPosition = it
            quantityToAccept = it.deliveredQuantity
        }
    }
}
