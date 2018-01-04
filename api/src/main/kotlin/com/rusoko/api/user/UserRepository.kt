package com.rusoko.api.user

import com.rusoko.api.dto.UserConfigurationDto
import com.rusoko.api.dto.UserRegisterDto

interface UserRepository {
    fun add(userDto: UserRegisterDto): Int
    fun configure(userDto: UserConfigurationDto)
    fun exist(userMail: String): Boolean
}