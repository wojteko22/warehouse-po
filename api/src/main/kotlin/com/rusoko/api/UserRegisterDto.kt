package com.rusoko.api

class UserRegisterDto(val name: String, val surname: String, val email: String, val permissions: Set<String>)