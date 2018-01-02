package com.rusoko.api.user

class UserRegisterDto(val name: String, val surname: String, val email: String, val permissions: Set<String>)