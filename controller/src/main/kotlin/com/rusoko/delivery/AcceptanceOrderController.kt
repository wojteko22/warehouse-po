package com.rusoko.delivery

import com.rusoko.api.AcceptanceOrderRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/acceptance-orders")
class AcceptanceOrderController(private val repository: AcceptanceOrderRepository) {

    @GetMapping
    fun getUnhandled() = repository.unhandled
}