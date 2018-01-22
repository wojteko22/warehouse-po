package com.rusoko.delivery

import com.rusoko.api.AcceptanceOrderRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/acceptance-orders")
class AcceptanceOrderController(private val repository: AcceptanceOrderRepository) {

    @GetMapping
    fun getUnhandled() = repository.unhandled

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int) = repository[id]

    @PatchMapping("/{id}")
    fun confirm(@PathVariable id: Int) = repository.confirm(id)
}