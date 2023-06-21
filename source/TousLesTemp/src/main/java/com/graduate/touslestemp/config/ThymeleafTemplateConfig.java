package com.graduate.touslestemp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * @File: ThymeleafTemplateConfig.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:10 AM
 * @Update: 21/6/2023
 */
@Configuration
public class ThymeleafTemplateConfig {

    /**
     * Configures and returns the template resolver for HTML templates.
     *
     * @return The configured HTML template resolver.
     */
    @Bean
    public ITemplateResolver templateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    /**
     * Configures and returns the Spring Template Engine.
     *
     * @param templateResolver The template resolver to be set for the template engine.
     * @return The configured Spring Template Engine.
     */
    @Bean
    public SpringTemplateEngine springTemplateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
}
