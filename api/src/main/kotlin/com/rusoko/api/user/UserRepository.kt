package com.rusoko.api.user

import com.rusoko.api.dto.UserRegisterDto

interface UserRepository {
    fun add(userDto: UserRegisterDto)
}