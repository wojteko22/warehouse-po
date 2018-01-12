package com.rusoko.user

import com.rusoko.api.dto.UserConfigurationDto
import com.rusoko.api.dto.UserRegisterDto
import com.rusoko.api.UserRepository
import com.rusoko.user.mail.Mail
import com.rusoko.user.mail.MailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(private val repository: UserRepository) {
    @Autowired private lateinit var mailService: MailService

    @PostMapping("/add")
    fun addUser(@RequestBody userRegisterInfo: UserRegisterDto) {
        val userId = repository.add(userRegisterInfo)
        val mailInfo = Mail.getFromUserRegisterInfo(userRegisterInfo)
        mailService.sendEmail(mailInfo, userId)
    }

    @PostMapping("/configure")
    fun configureUser(@RequestBody userConfigurationDto: UserConfigurationDto) =
            repository.configure(userConfigurationDto)

    @GetMapping("/exist/{userMail}")
    fun checkIfExist(@PathVariable userMail: String): Boolean =
            repository.exist(userMail)

    @GetMapping("/permissions")
    fun getPermission(): List<String> =
            repository.permissions

}