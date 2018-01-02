package com.rusoko.user

import com.rusoko.api.user.UserConfigurationDto
import com.rusoko.api.user.UserRegisterDto
import com.rusoko.api.user.UserRepository
import com.rusoko.user.mail.Mail
import com.rusoko.user.mail.MailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/com/rusoko/core/db/user")
class UserController(private val repository: UserRepository) {
    @Autowired private lateinit var mailService: MailService

    @PostMapping("/add")
    private fun addUser(@RequestBody userRegisterInfo: UserRegisterDto) {
        repository.add(userRegisterInfo)
        val mailInfo = Mail.getFromUserRegisterInfo(userRegisterInfo)
        mailService.sendEmail(mailInfo)
    }

    @PostMapping("/configure")
    private fun configureUser(@RequestBody userConfigurationDto: UserConfigurationDto) {

    }

}