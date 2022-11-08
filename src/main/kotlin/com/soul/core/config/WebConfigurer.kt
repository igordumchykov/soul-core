package com.soul.core.config

import org.slf4j.LoggerFactory
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.multipart.commons.CommonsMultipartResolver
import javax.servlet.ServletContext
import javax.servlet.ServletException


/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
class WebConfigurer(

    private val env: Environment,
    private val properties: ApplicationProperties
) : ServletContextInitializer {


    private val log = LoggerFactory.getLogger(javaClass)

    @Throws(ServletException::class)
    override fun onStartup(servletContext: ServletContext) {
        if (env.activeProfiles.isNotEmpty()) {
            log.info("Web application configuration, using profiles: {}", *env.activeProfiles as Array<*>)
        }
        log.info("Web application fully configured")
    }

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val cors = properties.security.cors
        val config = CorsConfiguration()
        config.allowedOriginPatterns = cors.allowedOriginPatterns
        config.allowCredentials = cors.allowCredentials
        config.allowedHeaders = cors.allowedHeaders
        config.allowedMethods = cors.allowedMethods
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }

    @Bean(name = ["multipartResolver"])
    fun multipartResolver(): CommonsMultipartResolver? {
        val multipartResolver = CommonsMultipartResolver()
        multipartResolver.setMaxUploadSize(100000)
        return CommonsMultipartResolver()
    }
}
