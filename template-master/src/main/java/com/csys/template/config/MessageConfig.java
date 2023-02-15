/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author Farouk
 */
@Configuration
public class MessageConfig {



    //@RefreshScope
    @Bean
    @Profile("prod")
    public ExposedResourceMessageBundleSource messageSource() {
        ExposedResourceMessageBundleSource messageSource = new ExposedResourceMessageBundleSource();

        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setBasename("classpath:/i18n/messages");
        return messageSource;
    }
}
