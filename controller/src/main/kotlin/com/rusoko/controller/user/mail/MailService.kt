package com.rusoko.controller.user.mail

import freemarker.template.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils

@Service
class MailService {
    @Autowired private lateinit var sender: JavaMailSender
    @Autowired private lateinit var freemarkerConfig: Configuration
    @Autowired private lateinit var mailProperties: MailProperties

    fun sendEmail(mailInfo: Mail) {
        val message = sender.createMimeMessage()
        val helper = MimeMessageHelper(message)
        val template = freemarkerConfig.getTemplate(mailProperties.templateName)
        val configurationLinkPrefix = mailProperties.configurationLinkPrefix ?: throw NullPointerException("Configuration link can not be null")
        val text = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailInfo.contentDataModel(configurationLinkPrefix))

        helper.setTo(mailInfo.userMail)
        helper.setText(text, true)
        helper.setSubject(mailInfo.subject)

        sender.send(message)
    }
}