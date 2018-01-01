package com.rusoko.controller.user.mail

import com.rusoko.api.UserRegisterDto

data class Mail(private val userName: String, val userMail: String, val subject: String) {
    fun contentDataModel(configurationLinkPrefix: String): Map<String, String> =
            mapOf(
                    Pair("user", userName),
                    Pair("link", getConfigurationLink(configurationLinkPrefix))
            )

    private fun getConfigurationLink(configurationLinkPrefix: String): String =
            "$configurationLinkPrefix/${userMail.substringBeforeLast('.')}"

    companion object {
        private val emailSubject = "Mail konfiguracyjny dla konta użytkownika w systemie hurtowni pasz"
        fun getFromUserRegisterInfo(userRegisterInfo: UserRegisterDto, subject: String = emailSubject): Mail =
                Mail(userRegisterInfo.name, userRegisterInfo.email, subject)
    }
}