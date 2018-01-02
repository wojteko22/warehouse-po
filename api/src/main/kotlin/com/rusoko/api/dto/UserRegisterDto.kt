package com.rusoko.api.dto

class UserRegisterDto(val name: String, val surname: String, val email: String, val permissions: Set<String>)