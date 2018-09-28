package com.rusoko

import com.rusoko.api.DataInitializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class ControllerApplication {
    @Value("\${allowedOrigins:http://localhost:4200}")
    private lateinit var allowedOrigins: Array<String>

    @Bean
    fun corsConfigurer(): WebMvcConfigurer =
            object : WebMvcConfigurer {
                override fun addCorsMappings(registry: CorsRegistry) {
                    registry.addMapping("/**").allowedOrigins(*allowedOrigins)
                            .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                }
            }
}

fun main(args: Array<String>) {
    val context = SpringApplication.run(ControllerApplication::class.java, *args)
    context.getBean(DataInitializer::class.java).init()
}
