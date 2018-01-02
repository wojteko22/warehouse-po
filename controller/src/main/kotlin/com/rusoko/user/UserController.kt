package com.rusoko.user

import com.rusoko.api.dto.UserRegisterDto
import com.rusoko.user.mail.Mail
import com.rusoko.user.mail.MailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class UserController {
    @Autowired private lateinit var mailService: MailService

    @RequestMapping("/addUser")
    private fun addUser(@RequestBody userRegisterInfo: UserRegisterDto) {
//        todo: trzeba zaimplementować dodawanie na backendzie
        val mailInfo = Mail.getFromUserRegisterInfo(userRegisterInfo)
        mailService.sendEmail(mailInfo)
    }

}