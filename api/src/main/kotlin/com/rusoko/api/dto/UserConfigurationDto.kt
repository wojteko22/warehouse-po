package com.rusoko.api.dto

data class UserConfigurationDto(val id: Int, val pesel: String, val password: String, val address: AddressDto)