package com.rusoko

import com.rusoko.api.DeliveryOrderRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/delivery-orders")
class DeliveryOrderController(private val repository: DeliveryOrderRepository) {

    @GetMapping
    fun getAll() = repository.all

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int) = repository[id]

    @PutMapping("/{id}/difference-report")
    fun createDefaultDifferenceReport(@PathVariable id: Int): ResponseEntity<Any> {
        repository.createDefaultDifferenceReport(id)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}/difference-report")
    fun getDifferenceReport(@PathVariable id: Int) = repository.differenceReport(id)

    @GetMapping("/{id}/difference-report/available-commodities")
    fun availableCommodities(@PathVariable id: Int) = repository.availableCommodities(id)
}