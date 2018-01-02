package com.rusoko.user

import com.rusoko.api.user.UserConfigurationDto
import com.rusoko.api.user.UserRegisterDto
import com.rusoko.user.mail.Mail
import com.rusoko.user.mail.MailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class UserController {
    @Autowired private lateinit var mailService: MailService

    @PostMapping("/addUser")
    private fun addUser(@RequestBody userRegisterInfo: UserRegisterDto) {
//        todo: trzeba zaimplementować dodawanie na backendzie
        val mailInfo = Mail.getFromUserRegisterInfo(userRegisterInfo)
        mailService.sendEmail(mailInfo)
    }

    @PostMapping("/configureUser")
    private fun configureUser(@RequestBody userConfigurationDto: UserConfigurationDto) {

    }

}