package com.rusoko.delivery

import com.rusoko.api.RegistrationDocumentRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/registration-documents")
class RegistrationDocumentController(private val repository: RegistrationDocumentRepository) {

    @PostMapping
    fun savePosition(@RequestParam acceptanceOrderId: Int) {
        repository.generate(acceptanceOrderId)
    }
}