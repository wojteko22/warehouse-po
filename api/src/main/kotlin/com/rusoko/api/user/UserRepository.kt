package com.rusoko.api.user

interface UserRepository {
    fun add(userDto: UserRegisterDto)
}