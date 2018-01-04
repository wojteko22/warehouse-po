package com.rusoko.delivery

import com.rusoko.api.DifferenceReportPositionRepository
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("/difference-report-positions")
class DifferenceReportPositionController(private val repository: DifferenceReportPositionRepository) {

    @PatchMapping("/{positionId}")
    fun savePosition(@PathVariable positionId: Int, @RequestParam deliveredQuantity: BigDecimal) {
        repository.savePosition(positionId, deliveredQuantity)
    }
}