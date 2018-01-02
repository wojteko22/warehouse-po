package com.rusoko.user.mail

import com.rusoko.api.dto.UserRegisterDto

data class Mail(private val userName: String, val userMail: String, val subject: String) {
    fun contentDataModel(configurationLink: String): Map<String, String> =
            mapOf(
                    Pair("user", userName),
                    Pair("link", configurationLink)
            )

    companion object {
        private val emailSubject = "Mail konfiguracyjny dla konta użytkownika w systemie hurtowni pasz"
        fun getFromUserRegisterInfo(userRegisterInfo: UserRegisterDto, subject: String = emailSubject): Mail =
                Mail(userRegisterInfo.name, userRegisterInfo.email, subject)
    }
}