package com.rusoko.user.mail

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "mail")
class MailProperties {
    var templateName: String? = null
    var configurationLinkPrefix: String? = null
}