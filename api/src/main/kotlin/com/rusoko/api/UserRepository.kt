package com.rusoko.api

import com.rusoko.api.dto.UserConfigurationDto
import com.rusoko.api.dto.UserRegisterDto

interface UserRepository {
    fun add(userDto: UserRegisterDto): Int
    fun configure(userDto: UserConfigurationDto)
    fun exist(userMail: String): Boolean
    val permissions: List<String>
}