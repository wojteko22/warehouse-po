package com.rusoko.api

import com.rusoko.api.dto.UserConfigurationDto
import com.rusoko.api.dto.UserRegisterDto

/**
 * Repository of users allowing to cooperate with users in data base
 */
interface UserRepository {
    /**
     * Insert a new user to data base
     * @param userDto information's about inserted user
     * @return inserted user id
     */
    fun add(userDto: UserRegisterDto): Int

    /**
     * Configure <update> user account about configuration information's
     * @param userDto configuration information's
     */
    fun configure(userDto: UserConfigurationDto)

    /**
     * Check if exist user account this specified email address
     * @param userMail checked user email address
     * @return true if user with specified email already exist
     */
    fun exist(userMail: String): Boolean

    /**
     * @property permissions list of all available user permissions
     */
    val permissions: List<String>
}