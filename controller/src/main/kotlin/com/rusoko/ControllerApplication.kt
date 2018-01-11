package com.rusoko

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@SpringBootApplication
class ControllerApplication {
    @Value("\${allowedOrigins:http://localhost:4200}")
    private lateinit var allowedOrigins: Array<String>

    @Bean
    fun corsConfigurer(): WebMvcConfigurer =
            object : WebMvcConfigurerAdapter() {
                override fun addCorsMappings(registry: CorsRegistry?) {
                    registry!!.addMapping("/**").allowedOrigins(*allowedOrigins)
                            .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                }
            }
}

fun main(args: Array<String>) {
    SpringApplication.run(ControllerApplication::class.java, *args)
}
