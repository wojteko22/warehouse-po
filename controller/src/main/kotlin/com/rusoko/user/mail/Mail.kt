package com.rusoko.user.mail

import com.rusoko.api.UserRegisterDto

data class Mail(private val userName: String, val userMail: String, val subject: String) {
    val model: Map<String, String>
        get() = mapOf(Pair("user", userName))

    companion object {
        private val emailSubject = "Mail konfiguracyjny dla konta użytkownika w systemie hurtowni pasz"
        fun getFromUserRegisterInfo(userRegisterInfo: UserRegisterDto, subject: String = emailSubject): Mail =
                Mail(userRegisterInfo.name, userRegisterInfo.email, subject)
    }
}