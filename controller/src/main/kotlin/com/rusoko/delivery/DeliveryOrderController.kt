package com.rusoko.delivery

import com.rusoko.api.DeliveryOrderRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/delivery-orders")
class DeliveryOrderController(private val repository: DeliveryOrderRepository) {

    @GetMapping
    fun getUnverified() = repository.unverified

    @GetMapping("/{orderId}")
    fun getById(@PathVariable orderId: Int) = repository[orderId]
}