package com.rusoko

import com.rusoko.api.DeliveryOrderRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/delivery-orders")
class DeliveryOrderController(private val repository: DeliveryOrderRepository) {

    @GetMapping
    fun get() = repository.all
}