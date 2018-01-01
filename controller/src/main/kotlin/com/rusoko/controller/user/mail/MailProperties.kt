package com.rusoko.controller.user.mail

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "mail")
class MailProperties {
    val mailTemplateName = "configurationEmailTemplate.ftl"
}