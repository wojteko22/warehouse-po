package com.rusoko

import com.rusoko.api.DifferenceReportRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/difference-reports")
class DifferenceReportController(private val repository: DifferenceReportRepository) {

    @PostMapping
    fun createDefaultDifferenceReport(@RequestParam deliveryOrderId: Int): ResponseEntity<Int> {
        val differenceReportId = repository.createDefault(deliveryOrderId)
        return ResponseEntity(differenceReportId, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int) = repository[id]

    @GetMapping("/{id}/available-commodities")
    fun availableCommodities(@PathVariable id: Int) = repository.availableCommodities(id)

    @PostMapping("/{id}/positions")
    fun addCommodity(@PathVariable id: Int, @RequestParam commodityId: Int) {
        repository.addCommodity(id, commodityId)
    }
}